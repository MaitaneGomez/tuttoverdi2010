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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		List <RehearsalDO> lista = new ArrayList<RehearsalDO>();
		
		try 
		{
			dataBase.connect("scalaMilano");
			lista = dataBase.getRehearsals();
			//falta la lista de scalaNapoli
			dataBase.disconnect();
			
			//ahora habria que coger las reservas
			
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
		return rehearsalCache;
	}

	public static void main(String[] args) {

	}

	
	
	
	@Override
	public void addRemoteObserver(IRemoteObserver arg0) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRemoteObserver(IRemoteObserver arg0)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
