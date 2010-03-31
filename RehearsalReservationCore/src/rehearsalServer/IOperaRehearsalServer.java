package rehearsalServer;

import java.rmi.RemoteException;
import java.util.List;

import rehearsalServer.loginGateway.ValidationException;


/**
 * REMOTE INTERFACE TO BE IMPLEMENTED BY THE REMOTE SERVER WHICH IS THE REMOTE
 * OBSERVABLE FACADE
 * 
 * TO BE COMPLETED BY THE STUDENTS
 */


public interface IOperaRehearsalServer extends util.observer.rmi.IRemoteObservable 
{
	public String login(String username, String password) throws ValidationException, RemoteException;
	public List<RehearsalRMIDTO> getRehearsals() throws RemoteException;
	
	
	
}
