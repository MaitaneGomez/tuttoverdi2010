/**
 * RMI SERVER  - THE REHEARSAL SERVER IN THE ARCHITECTURE
 * CONNECTION TO: RMI AUTHORIZATION SERVER AND AUTHORIZATION WEB SERVICE
 * CONNECTION TO: CORBA OPERA HOUSE COMPONENT
 * CONNECTION TO: EUSKALDUNA BIO WEB SERVICE
 * USE THE COMMAND INTERFACE TO KEEP A TRACE OF THE SERVER ACTIVITIES
 */
package rehearsalServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import rehearsalServer.houseGateway.IOperaHGateway;
import rehearsalServer.houseGateway.OperasHGatewayFactory;
import rehearsalServer.houseGateway.RehearsalDO;
import rehearsalServer.loginGateway.ValidationException;
import util.observer.rmi.IRemoteObserver;
import util.observer.rmi.RemoteObservable;

public class OperaRehearsalServer extends UnicastRemoteObject implements IOperaRehearsalServer{

	private static final long serialVersionUID = 1L;
	private util.observer.rmi.RemoteObservable remoteObservable;
	private Map<String, Map<String, RehearsalRMIDTO>> rehearsalCache;
	private OperasHGatewayFactory  gateway = null;


	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */
	
	//******************************************************************************************
	//******************************************************************************************
	
	public OperaRehearsalServer() throws RemoteException{
		super();
		this.remoteObservable = new RemoteObservable();
		rehearsalCache = createCache();
	}
	
	//******************************************************************************************
	//******************************************************************************************
	
	
	private Map<String, Map<String, RehearsalRMIDTO>> createCache()
	{
		List <RehearsalDO> rehearsalDOList;
		Map<String, Map<String, RehearsalRMIDTO>> rehearsalsCache = new TreeMap<String, Map<String,RehearsalRMIDTO>>();
		
		//esto se hace para acceder al metodo que ofrece el servidor corba, de forma transparente
		//a traves de los gateways y el singletone
		gateway = OperasHGatewayFactory.getInstance();
		IOperaHGateway corbaGate = gateway.getOperaHGateway("", "corba");
		rehearsalDOList = corbaGate.getRehearsals();
	
		ReservationCounter.connect();
		
		//primero recorremos para scalaMilano y nos creamos su map interno
		Map<String, RehearsalRMIDTO> scalaMilanoMAP = new TreeMap<String, RehearsalRMIDTO>();
		for(int i = 0; i<rehearsalDOList.size(); i++)
		{
			RehearsalDO x = rehearsalDOList.get(i);
			int ocupiedSeats = ReservationCounter.obtainOcupiedSeats("ScalaMILANO", x.getOperaName());
			RehearsalRMIDTO newRehearsalRMIDTO = new RehearsalRMIDTO("ScalaMILANO", x.getOperaName(),x.getDate(),x.getAvailableSeats()-ocupiedSeats);
			scalaMilanoMAP.put(x.getOperaName(), newRehearsalRMIDTO);
		}
		ReservationCounter.disconnect();
		rehearsalsCache.put("ScalaMilano", scalaMilanoMAP);
			
		return rehearsalsCache;
	}
	
	//******************************************************************************************
	//******************************************************************************************
	
	
	public Map<String, Map<String, RehearsalRMIDTO>> getRehearsalCache() 
	{
		return rehearsalCache;
	}

	
	//******************************************************************************************
	//******************************************************************************************
	
	
	public static void main(String[] args) 
	{
		//suponemos que args tiene la ip el pueto y el nombre del servidor
		System.out.println("Getting access to the RMIAuthorization server...");
		
		//authorizationGate = AuthorizationGatewayFactory.getAuthGateway();
	}

	
	
	
	@Override
	public void addRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.addRemoteObserver(observer);
	}

	
	//******************************************************************************************
	//******************************************************************************************
	
	
	@Override
	public void deleteRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.deleteRemoteObserver(observer);
	}

	
	//******************************************************************************************
	//******************************************************************************************

	
	@Override
	public String login(String username, String password) throws ValidationException, RemoteException 
	{
		// TODO Auto-generated method stub
		return null;
	}
}
