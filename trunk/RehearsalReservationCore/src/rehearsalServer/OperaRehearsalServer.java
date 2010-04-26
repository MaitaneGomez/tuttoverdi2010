/**
 * RMI SERVER  - THE REHEARSAL SERVER IN THE ARCHITECTURE
 * CONNECTION TO: RMI AUTHORIZATION SERVER AND AUTHORIZATION WEB SERVICE
 * CONNECTION TO: CORBA OPERA HOUSE COMPONENT
 * CONNECTION TO: EUSKALDUNA BIO WEB SERVICE
 * USE THE COMMAND INTERFACE TO KEEP A TRACE OF THE SERVER ACTIVITIES
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

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import rehearsalServer.dao.RehearsalServerDAO;
import rehearsalServer.houseGateway.IOperaHGateway;
import rehearsalServer.houseGateway.OperasHGatewayFactory;
import rehearsalServer.houseGateway.RehearsalDO;
import rehearsalServer.loginGateway.AuthorizationGatewayFactory;
import rehearsalServer.loginGateway.IAuthorizeGateway;
import rehearsalServer.loginGateway.ValidationException;
import rehearsalServer.saxParser.ArrayGateways;
import rehearsalServer.saxParser.GatewayObject;
import rehearsalServer.saxParser.HouseGatewaysSAXHandler;
import util.observer.rmi.IRemoteObserver;
import util.observer.rmi.RemoteObservable;

public class OperaRehearsalServer extends UnicastRemoteObject implements IOperaRehearsalServer{

	private static final long serialVersionUID = 1L;
	private util.observer.rmi.RemoteObservable remoteObservable;
	private static Map<String, Map<String, RehearsalRMIDTO>> rehearsalCache;
	private static IAuthorizeGateway gatewayAuth = null;
	private static ArrayList <IOperaHGateway> arrayGateways;
	private static RehearsalServerDAO dao = null;


	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */


	public OperaRehearsalServer(String[] args) throws RemoteException{
		super();
		this.remoteObservable = new RemoteObservable();
	}

	//*************************************************************************
	
	private static void parse(String xmlFile)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		
		try
		{
			System.out.println("Parsing using SAX...");
			
			SAXParser saxParser = factory.newSAXParser();
			HouseGatewaysSAXHandler handler = new HouseGatewaysSAXHandler();
			saxParser.parse(xmlFile, handler);
			
			ArrayGateways arrayXMLGateways = new ArrayGateways();
			arrayXMLGateways = handler.getArrayXMLGateways();
			
			//ahora nos creamos el array de gateways pero estos ya son gateways reales
			//no son nodos del xml
			
			arrayGateways = new ArrayList<IOperaHGateway>();
			
			//ahora recorremos el arrayXML para coger los datos de los distintos
			//servicios
			
			int size = arrayXMLGateways.getArrayXMLGateway().size();
			for(int i = 0; i < size; i++)
			{
				String serviceUri = "";
				GatewayObject gateway = new GatewayObject();
				gateway = arrayXMLGateways.getArrayXMLGateway().get(i);
				
				
				List <String> details = gateway.getDetails();
				for(int j = 0; j < details.size(); j++)
				{
					serviceUri = serviceUri + " " + details.get(j);
				}
				serviceUri = serviceUri + " " + gateway.getName();
				String tech = gateway.getTech();
				

				arrayGateways.add(OperasHGatewayFactory.getInstance().getOperaHGateway(serviceUri,tech));
				System.out.println("Gateway to " + gateway.getName() + " service created");
			}
		}
		catch (Exception e)
		{
			System.out.println("ERROR --> Main(): " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	
	//**************************************************************************
	
	
	private static Map<String, Map<String, RehearsalRMIDTO>> createCache()
	{
		List <RehearsalDO> rehearsalDOList;
		Map<String, Map<String, RehearsalRMIDTO>> rehearsalsCache = new TreeMap<String, Map<String,RehearsalRMIDTO>>();


		for(int i = 0; i < arrayGateways.size(); i++)
		{
			Map<String, RehearsalRMIDTO> internalMAP = new TreeMap<String, RehearsalRMIDTO>();
			rehearsalDOList = arrayGateways.get(i).getRehearsals();
			String serverName = null;
			serverName = arrayGateways.get(i).getServerName();
			System.out.println("connection to " + serverName + " : OK");
			for(int j = 0; j<rehearsalDOList.size(); j++)
			{	
				RehearsalDO x = rehearsalDOList.get(j);
				int ocupiedSeats = dao.getReservationsCount(serverName, x.getOperaName());
				RehearsalRMIDTO newRehearsalsRMIDTO = new RehearsalRMIDTO(serverName, x.getOperaName(),x.getDate(),x.getAvailableSeats()-ocupiedSeats);
				internalMAP.put(x.getOperaName(), newRehearsalsRMIDTO);
			}
			rehearsalsCache.put(serverName, internalMAP);
		}

		return rehearsalsCache;
	}


	//Para la prueba
	public Map<String, Map<String, RehearsalRMIDTO>> getRehearsalCache() 
	{
		return rehearsalCache;
	}


	public static void main(String[] args) 
	{
		System.out.println("REHEARSAL RESERVATION SERVER CONSOLE");


		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			IOperaRehearsalServer server = new OperaRehearsalServer(args);
			String name = "//" + args[3] + ":" + args[4] + "/" + args[5];
			java.rmi.Naming.rebind(name, server);
			System.out.println("Registration to the RMI Registry: OK");

			gatewayAuth = (AuthorizationGatewayFactory.getInstance()).getAuthGateway(args[6]);
			gatewayAuth.initializeParameters(args);

			parse(args[14]);


			dao = new RehearsalServerDAO();
			System.out.println("Accesing to the Reservations DB");
			dao.connect();
			System.out.println("INIT Process: Loading the cache of RehearsalRMIDTO objects...");
			rehearsalCache = createCache();
			dao.disconnect();
			System.out.println("Rehearsal Reservation Server Active and Running...");

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
		System.out.println("user trying to log in....");
		System.out.println("authorized user: " + studentName);
		return studentName;
	}


	@Override
	public List<RehearsalRMIDTO> getRehearsals() throws RemoteException 
	{

		List <RehearsalRMIDTO> rehearsals = new ArrayList<RehearsalRMIDTO>();
		Iterator<Entry<String, Map<String, RehearsalRMIDTO>>> itGeneralMap = rehearsalCache.entrySet().iterator();

		System.out.println("Obtaning rehearsals from the cache...");

		while(itGeneralMap.hasNext())
		{
			Map.Entry<String, Map<String, RehearsalRMIDTO>> entry = (Map.Entry<String, Map<String, RehearsalRMIDTO>>) itGeneralMap.next();
			Map<String,RehearsalRMIDTO> internalMap = entry.getValue();
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
	public  int reserveSeat(String studName, String OperaHouse, String OperaName) throws RemoteException 
	{

		Map <String, RehearsalRMIDTO> internalMap = rehearsalCache.get(OperaHouse);
		RehearsalRMIDTO DTO = internalMap.get(OperaName);
		System.out.println("Making a reservation...");


		if(DTO.getAvailableSeats()>0) //Hay asientos libres
		{
			DTO.setAvailableSeats(DTO.getAvailableSeats()-1);
			System.out.println("Reservation data: " + studName + " " + OperaName + " " + OperaHouse + " " + DTO.getAvailableSeats());
			internalMap.put(OperaName, DTO);
			rehearsalCache.put(OperaHouse, internalMap);
			dao.connect();
			dao.reserveSeat(studName, OperaHouse, OperaName);
			dao.disconnect();

			//Notificamos a los demas clientes
			System.out.println("Notifying to all conected users...");
			this.notifyAll(DTO);	
			return 1;
		}
		else
		{
			return -1;
		}
	}

	private void notifyAll(RehearsalRMIDTO DTO)
	{
		this.remoteObservable.notifyRemoteObservers(DTO);
	}
}
