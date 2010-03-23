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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import corbaServer.RehearsalDO;
import corbaServer.dao.CorbaOperaHouseDAO;

import util.observer.rmi.IRemoteObserver;
import util.observer.rmi.RemoteObservable;

public class OperaRehearsalServer extends UnicastRemoteObject implements IOperaRehearsalServer{

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private util.observer.rmi.RemoteObservable remoteObservable;
	private Map<String, Map<String, RehearsalRMIDTO>> rehearsalCache;


	/**
	 * CACHE OF RehearsalRMIDTO objects, organized by Opera House Name To be
	 * loaded at the initialization process
	 */
	

	
	public OperaRehearsalServer() throws RemoteException{
		super();
		this.remoteObservable = new RemoteObservable();
		rehearsalCache = createCache();
	}
	
	
	private Map<String, Map<String, RehearsalRMIDTO>> createCache()
	{
		CorbaOperaHouseDAO dataBase = new CorbaOperaHouseDAO();
		List <RehearsalDO> rehearsalDOList = new ArrayList<RehearsalDO>();
		Map<String, Map<String, RehearsalRMIDTO>> rehearsalsCache = new TreeMap<String, Map<String,RehearsalRMIDTO>>();
		
		try 
		{
			dataBase.connect("scalaMilano");
			rehearsalDOList = dataBase.getRehearsals();
			//falta la lista de scalaNapoli para la segunda entrega
			dataBase.disconnect();
			
			//ahora habria que coger las reservas y obtener sus asientos ocupados
			
			//conectamos la base de datos de reservas
			ReservationCounter.connect();
			
			//primero recorremos para scalaMilano y nos creamos su map interno
			Map<String, RehearsalRMIDTO> scalaMilanoMAP = new TreeMap<String, RehearsalRMIDTO>();
			for(int i = 0; i<rehearsalDOList.size(); i++)
			{
				RehearsalDO x = rehearsalDOList.get(i);
				int ocupiedSeats = ReservationCounter.obtainOcupiedSeats("ScalaMILANO", x.getOperaName());
				RehearsalRMIDTO newRehearsalRMIDTO = new RehearsalRMIDTO("ScalaMILANO", x.getOperaName(),x.getDate(),x.getSeats()-ocupiedSeats);
				scalaMilanoMAP.put(x.getOperaName(), newRehearsalRMIDTO);
			}
			ReservationCounter.disconnect();
			rehearsalsCache.put("ScalaMilano", scalaMilanoMAP);
			
			
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rehearsalsCache;
	}
	
	public Map<String, Map<String, RehearsalRMIDTO>> getRehearsalCache() 
	{
		return rehearsalCache;
	}

	
	
	public static void main(String[] args) {

	}

	
	
	
	@Override
	public void addRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.addRemoteObserver(observer);
		
		try
		{
			//preguntar de que tipo es el parametro (sitios availables?)
			//observer.update(new Integer(this))
		}
		catch(RemoteException e)
		{
			System.err.println("ERROR en notificacion al registrarse: " + e.getMessage() );
		}
		
	}

	@Override
	public void deleteRemoteObserver(IRemoteObserver observer) throws RemoteException 
	{
		this.remoteObservable.deleteRemoteObserver(observer);
	}
}
