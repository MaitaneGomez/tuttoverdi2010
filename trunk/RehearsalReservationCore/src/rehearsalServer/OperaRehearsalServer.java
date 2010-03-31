/**
 * RMI SERVER  - THE REHEARSAL SERVER IN THE ARCHITECTURE
 * CONNECTION TO: RMI AUTHORIZATION SERVER AND AUTHORIZATION WEB SERVICE
 * CONNECTION TO: CORBA OPERA HOUSE COMPONENT
 * CONNECTION TO: EUSKALDUNA BIO WEB SERVICE
 * USE THE COMMAND INTERFACE TO KEEP A TRACE OF THE SERVER ACTIVITIES
 * drymearñoiganhdfsoignañwioefniño
 */
package rehearsalServer;

import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import rehearsalServer.houseGateway.IOperaHGateway;
import rehearsalServer.houseGateway.OperasHGatewayFactory;
import rehearsalServer.houseGateway.RehearsalDO;
import rehearsalServer.loginGateway.AuthorizationGatewayFactory;
import rehearsalServer.loginGateway.IAuthorizeGateway;
import rehearsalServer.loginGateway.ValidationException;
import util.observer.rmi.IRemoteObserver;
import util.observer.rmi.RemoteObservable;

public class OperaRehearsalServer extends UnicastRemoteObject implements IOperaRehearsalServer{

	private static final long serialVersionUID = 1L;
	private util.observer.rmi.RemoteObservable remoteObservable;
	private Map<String, Map<String, RehearsalRMIDTO>> rehearsalCache;
	private OperasHGatewayFactory gateway = null; 
	private static IAuthorizeGateway gatewayAuth = null;


	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */
	

	
	public OperaRehearsalServer(String[] args) throws RemoteException{
		super();
		this.remoteObservable = new RemoteObservable();
		rehearsalCache = createCache(args);
	}
	
	
	private Map<String, Map<String, RehearsalRMIDTO>> createCache(String [] args)
	{
		List <RehearsalDO> rehearsalDOList;
		Map<String, Map<String, RehearsalRMIDTO>> rehearsalsCache = new TreeMap<String, Map<String,RehearsalRMIDTO>>();
		
		gateway= OperasHGatewayFactory.getInstance();
		IOperaHGateway corbaGate= gateway.getOperaHGateway(args[0]+" "+args[1]+" "+args[2],"corba");
		rehearsalDOList = corbaGate.getRehearsals();
				
			
			
		ReservationCounter.connect();
			
		//primero recorremos para scalaMilano y nos creamos su map interno
		
		Map<String, RehearsalRMIDTO> scalaMilanoMAP = new TreeMap<String, RehearsalRMIDTO>();
		for(int i = 0; i<rehearsalDOList.size(); i++)
		{
			RehearsalDO x = rehearsalDOList.get(i);
			int ocupiedSeats = ReservationCounter.obtainOcupiedSeats("ScalaMILANO", x.getOperaName());
			RehearsalRMIDTO newRehearsalsRMIDTO = new RehearsalRMIDTO("ScalaMILANO", x.getOperaName(),x.getDate(),x.getAvailableSeats()-ocupiedSeats);
			scalaMilanoMAP.put(x.getOperaName(), newRehearsalsRMIDTO);
		}
		ReservationCounter.disconnect();
		rehearsalsCache.put("ScalaMilano", scalaMilanoMAP);
			
		return rehearsalsCache;
	}
	
	public Map<String, Map<String, RehearsalRMIDTO>> getRehearsalCache() 
	{
		return rehearsalCache;
	}

	
	
	public static void main(String[] args) 
	{
		
		gatewayAuth = (AuthorizationGatewayFactory.getInstance()).getAuthGateway(args[6]);
		gatewayAuth.initializeParameters(args);
		
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try {
			IOperaRehearsalServer server = new OperaRehearsalServer(args);
			String name = "//" + args[3] + ":" + args[4] + "/" + args[5];
			java.rmi.Naming.rebind(name, server);
			System.out.println("registrado");
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	
	@Override
	public void addRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.addRemoteObserver(observer);
		
	}

	@Override
	public void deleteRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.deleteRemoteObserver(observer);
	}


	@Override
	public String login(String username, String password) throws ValidationException, RemoteException 
	{
		String studentName = gatewayAuth.login(username, password);
		return studentName;
	}


	@Override
	public List<RehearsalRMIDTO> getRehearsals() throws RemoteException 
	{
		
		List<RehearsalRMIDTO> rehearsals = new ArrayList<RehearsalRMIDTO>();
		Iterator<Entry<String, Map<String, RehearsalRMIDTO>>> itGeneralMap = rehearsalCache.entrySet().iterator();
		while(itGeneralMap.hasNext())
		{
			Map.Entry<String, Map<String, RehearsalRMIDTO>> entry = (Map.Entry<String, Map<String,RehearsalRMIDTO>>) itGeneralMap.next();
			Map<String, RehearsalRMIDTO> internalMap = entry.getValue();
			Iterator<Entry<String, RehearsalRMIDTO>> itInternalMap = internalMap.entrySet().iterator();
			while(itInternalMap.hasNext())
			{
				Map.Entry<String, RehearsalRMIDTO> entry2 = (Entry<String, RehearsalRMIDTO>) itInternalMap.next();
				RehearsalRMIDTO DTO = entry2.getValue();
				rehearsals.add(DTO);
			}
		}
		return rehearsals;
	}


	@Override
	public synchronized void reserveSeat(String studName, String OperaHouse, String OperaName) throws RemoteException 
	{
		Map <String, RehearsalRMIDTO> internalMap = rehearsalCache.get(OperaHouse);
		RehearsalRMIDTO DTO = internalMap.get(OperaName);
		System.out.println(" no llego");
		if(DTO.getAvailableSeats()>0) //hay asientos libres
		{
			DTO.setAvailableSeats(DTO.getAvailableSeats()-1);
			System.out.println(DTO.getOperaName() + " " + DTO.getOperaHouse() + " " + DTO.getAvailableSeats());
			internalMap.put(OperaName, DTO);
			rehearsalCache.put(OperaHouse, internalMap);
			ReservationCounter.connect();
			ReservationCounter.reserve(DTO, studName);
			ReservationCounter.disconnect();
			
			//notificamos a los demas clientes
			this.notifyAll(DTO);

		}
		
		
	}
	
	private void notifyAll(RehearsalRMIDTO DTO)
	{
		this.remoteObservable.notifyRemoteObservers(DTO);
	}
}
