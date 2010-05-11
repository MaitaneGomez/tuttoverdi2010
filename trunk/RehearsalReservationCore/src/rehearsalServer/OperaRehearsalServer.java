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


//THIS CLASS IS THE MOST IMPORTANT
//ITS AIM IS TO CONNECT WITH ALL THE SERVICES AN RETRIEVE FROM THEM
//THE DIFFERENT REHEARSALS AND LOADED THEM INTO A CACHE.
//IT ALSO IMPLEMENTS THE REMOTE METHODS AND IT IS AN OBSERVABLE


public class OperaRehearsalServer extends UnicastRemoteObject implements IOperaRehearsalServer{

	private static final long serialVersionUID = 1L;
	//THIS ATTRIBUTE HAS INSIDE A LIST OF OBSERVERS WHICH WILL BE
	//SCAN IN ORDER TO NOTIFY THEM ANY CHANGES 
	private util.observer.rmi.RemoteObservable remoteObservable;
	private static Map<String, Map<String, RehearsalRMIDTO>> rehearsalCache;
	//GATEWAY FOR THE AUTHORIZATION PURPOSE
	private static IAuthorizeGateway gatewayAuth = null;
	//ARRAY OF OPERAGATEWAYS. IT WILL BE CREATED WITH THE INFORMATION
	//OF THE XML FILE.IT IS AN ARRAY JUST TO ADD FLEXIBILITY TO THE PROJECT
	private static ArrayList <IOperaHGateway> arrayGateways;
	//DAO OBJECT TO ACCESS AN DEAL WITH THE RESERVATIONS DB
	private static RehearsalServerDAO dao = null;


	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */


	//CONSTRUCTOR. WE CREATE AN INSTANCE OF THE REMOTEOBSERVABLE(the one that has the list of observers)
	public OperaRehearsalServer() throws RemoteException{
		super();
		this.remoteObservable = new RemoteObservable();
	}

	//*************************************************************************
	
	//THIS METHOD PARSES THE XML FILE AND CREATES AN ARRAY OF GATEWAYOBJECTS
	private static void parse(String xmlFile)
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setValidating(true);
		
		try
		{
			System.out.println("Parsing using SAX...");
			
			SAXParser saxParser = factory.newSAXParser();
			//WE CREATE A HANDLER
			HouseGatewaysSAXHandler handler = new HouseGatewaysSAXHandler();
			//THE AHNDLER BEGINS TO PARSE
			saxParser.parse(xmlFile, handler);
			
			//WE CREATE AN INSTANCE OF ARRAYXMLGATEWAY AND WE ASIGNE TO IT
			//THE ARRAYXMLGATEWAY THAT THE HANDLER HAS CREATED
			ArrayGateways arrayXMLGateways = new ArrayGateways();
			arrayXMLGateways = handler.getArrayXMLGateways();
			
			//NOW, WE CREATE AN ARRAY OF GATEWAYS(OPERA)
			arrayGateways = new ArrayList<IOperaHGateway>();
			
			
			//NOW, WE SCAN THE ARRAYXMLGATEWAYS IN ORDER TO RETRIEVE THE INFORMATION
			//OF EACH OF ITS OBJECTS, JUST TO CREATE THE OPERA GATEWAYS
			int size = arrayXMLGateways.getArrayXMLGateway().size();
			for(int i = 0; i < size; i++)
			{
				String serviceUri = "";
				GatewayObject gateway = new GatewayObject();
				//WE OBTAIN ONE ELEMET OF THE ARRAYXMLGATEWAYS
				gateway = arrayXMLGateways.getArrayXMLGateway().get(i);
				
				//WE SCAN THE LIST OF DETAILS THAT HAS THE GATEWAYOBJECT
				//AND WITH THEM, WE CREATE THE SERVICEURI FOR THE OPERAGATEWAY
				List <String> details = gateway.getDetails();
				for(int j = 0; j < details.size(); j++)
				{
					serviceUri = serviceUri + " " + details.get(j);
				}
				//WE ADD THE NAME TO THE SERVICEURI
				serviceUri = serviceUri + " " + gateway.getName();
				String tech = gateway.getTech();
				

				//WE ADD A NEW GATEWAY TO THE ARRAY OF GATEWAYS (OPERAS)
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
	
	//THE AIM OF THIS METHOD IS TO LOAD THE CACHE OF REHEARSALS
	//IN ORDER TO DO THAT WE HAVE TO SCAN THE ARAY OF OPERA GATEWAYS AND
	//INVOKE THE GETREHEARSAL METHOD OF EACH OF THEM
	//FURTHERMORE WE HAVE TO CALCULATE DE FREE SEATS VIA THE DAO
	private static Map<String, Map<String, RehearsalRMIDTO>> createCache()
	{
		//LIST OF REHEARSALDO THAT ARE GOING TO BE RETURN BY THE GATEWAYS
		List <RehearsalDO> rehearsalDOList;
		Map<String, Map<String, RehearsalRMIDTO>> rehearsalsCache = new TreeMap<String, Map<String,RehearsalRMIDTO>>();


		//WE SCAN THE ARRAY OF OPERA GATEWAYS
		for(int i = 0; i < arrayGateways.size(); i++)
		{
			//WE CREATE THE INTERNAL MAP OF THE TREEMAP
			Map<String, RehearsalRMIDTO> internalMAP = new TreeMap<String, RehearsalRMIDTO>();
			rehearsalDOList = arrayGateways.get(i).getRehearsals(); //OBTAINING THE REHEARSALS FROM THE GATEWAY
			String serverName = null;
			serverName = arrayGateways.get(i).getServerName(); //WE OBTAIN THE NAME OF THE OPERAHOUSE THAT IS THE SAME OF THE SERVICE
			System.out.println("connection to " + serverName + " : OK");
			//WE SCAN THE LIST OF REHEARSALS IN ORDER TO KNOW THE FREE SEATS
			for(int j = 0; j<rehearsalDOList.size(); j++)
			{	
				RehearsalDO x = rehearsalDOList.get(j);
				//WE OBTAIN THE NUMBER OF OCUPIED SEATS
				//AND CREATE A REHEARSALRMIDTO IN ORDER TO PUT IT IN THE INTERNAL CACHE
				int ocupiedSeats = dao.getReservationsCount(serverName, x.getOperaName());
				RehearsalRMIDTO newRehearsalsRMIDTO = new RehearsalRMIDTO(serverName, x.getOperaName(),x.getDate(),x.getAvailableSeats()-ocupiedSeats);
				internalMAP.put(x.getOperaName(), newRehearsalsRMIDTO);
			}
			//WE PUT THE INTERNAL MAP IN THE TREEMAP
			rehearsalsCache.put(serverName, internalMAP);
		}

		return rehearsalsCache;
	}


	//Para la prueba
	public Map<String, Map<String, RehearsalRMIDTO>> getRehearsalCache() 
	{
		return rehearsalCache;
	}


	//MAIN METHOD WHOSE PURPOSE IS TO REGISTER A RMI OBJECT IN THE RMIREGISTRY
	//IN ADDITION IT LOADS THE CACHE AFTER PARSING THE XML FILE
	public static void main(String[] args) 
	{
		System.out.println("REHEARSAL RESERVATION SERVER CONSOLE");


		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}

		try {
			//WE CREATE A SERVER OBJECT AND A NAME FOR THE SAME
			//ARGS[0]: IP ARGS[1]: PORT ARGS[2]:NAME
			IOperaRehearsalServer server = new OperaRehearsalServer();
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			java.rmi.Naming.rebind(name, server);
			System.out.println("Registration to the RMI Registry: OK");
			
			//WE CREATE A GATEWAY FOR THE AUTHORIZATION (WS OR RMI)
			//ARGS[3] IS THE TECHNOLOGY USED
			//WE CALL TO INITIALIZE PARAMETERS TO HAVE THE SERVICE AUTHORIZATION
			//(RMI OBJECT OR WS) ACCESSIBLE
			gatewayAuth = (AuthorizationGatewayFactory.getInstance()).getAuthGateway(args[3]);
			gatewayAuth.initializeParameters(args);

			//WE PARSE THE XML FILE ARGS[6]:XML FILE
			parse(args[6]);


			//NOW WE AR GOING TO LOAD THE CACHE, SO WE NEED AN INSTANCE 
			//OF THE DAO
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
	//METHOD TO ADD A REMOTE OBSERVER TO THE LIST OF THE OBSERVABLE
	public void addRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.addRemoteObserver(observer);

	}

	@Override
	//METHOD TO DELETE A REMOTE OBSERVER FROM THE LIST OF THE OBSERVABLE
	public void deleteRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.deleteRemoteObserver(observer);
	}


	@Override
	//REMOTE METHOD OFFERED BY THE RMI RESERVATION SERVER
	//IT INVOKES THE LOGIN METHOD OF THE LOGIN GATEWAY (RMI OR WS)
	public String login(String username, String password) throws ValidationException, RemoteException 
	{
		String studentName = gatewayAuth.login(username, password);
		System.out.println("user trying to log in....");
		System.out.println("authorized user: " + studentName);
		return studentName;
	}


	@Override
	//REMOTE METHOD OFFERED BY THE RMI RESERVATION SERVER 
	//THAT RETURNS THE CACHE OR REHEARSALS.
	//IT WILL BE INVOKED WHEN A USER APLLIES FOR THE SCHEDULED REHEARSALS
	//NOTE******** CRYSTALLAB
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
	//REMOTE METHOD OFFERED BY THE RMI RESERVATION SERVER
	//WHOSE AIM IS TO CREATE A NEW ROW IN THE TABLE OF RESERVATIONS IN
	//RESERVATIONS BD. THE DAO IS THE ONE WHICH IS GOING TO DEAL WITH THE INSERT OPERATION
	//IT RETURN AN INT IN ORDER TO KNOW IF THE RESERVATION HAS BEES SUCCESFUL OR NOT(1, -1)
	public  int reserveSeat(String studName, String OperaHouse, String OperaName) throws RemoteException 
	{
		//WE OBTAIN THE INTERNAL MAP GIVEN THE OPERAHOUSE NAME
		Map <String, RehearsalRMIDTO> internalMap = rehearsalCache.get(OperaHouse);
		//WE OBTAIN THE REHEARSAL THAT IS GOING TO REDUCE ITS AVAILABLE
		//SEATS IN ONE
		RehearsalRMIDTO DTO = internalMap.get(OperaName);
		System.out.println("Making a reservation...");


		if(DTO.getAvailableSeats()>0) //THERE ARE AVAILABLE SEATS
		{
			//WE UPDATE THE REHEARSAL THE STUDENT IS GOING TO MAKE A RESERVATION FOR
			DTO.decAvailableSeats(1);
			System.out.println("Reservation data: " + studName + " " + OperaName + " " + OperaHouse + " " + DTO.getAvailableSeats());
			internalMap.put(OperaName, DTO);
			rehearsalCache.put(OperaHouse, internalMap);
			//WE EXECUTE THE RESERVIATION VIA DAO
			dao.connect();
			dao.reserveSeat(studName, OperaHouse, OperaName);
			dao.disconnect();

			//WE NOTIFY ALL THE CLIENTS (OBSERVER) THAT THE AVAILABLE
			//SEATS OF A REHEARSALS HAS BEEN DECREASED
			System.out.println("Notifying to all conected users...");
			//WE PASS THE DTO OF THE REHEARSAL THAT HAS BEEN MODIFIED
			this.notifyAll(DTO);	
			return 1; //1 MEANS OK
		}
		else
		{
			return -1; //-1 MEANS ERROR
		}
	}

	//METHOD THAT IS INVOKED WHEN A CLIENT MAKE A RESERVATION
	private void notifyAll(RehearsalRMIDTO DTO)
	{
		//WE INFORM THE OBSERVABLE THAT A REHEARSAL HAS CHANGED
		//IN ORDER TO SCAN ITS INTERNAL ARRAY OF OBSERVERS
		this.remoteObservable.notifyRemoteObservers(DTO);
	}
}
