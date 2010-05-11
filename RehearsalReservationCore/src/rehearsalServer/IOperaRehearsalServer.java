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

//THIS INTERFACE DECLARES ALL THE REMOTE SERVICES THAT THE RESERVATION 
//SERVER IS GOING TO PROVIDE TO ITS USERS
//IT IMPLENETS IREMOTEOBSERVABLE BECAUSE THE CLASS THAT IMPLEMENTS THIS INTERFACE
//IS GOING TO BE AN OBSERVABLE

public interface IOperaRehearsalServer extends util.observer.rmi.IRemoteObservable 
{
	public String login(String username, String password) throws ValidationException, RemoteException;
	public List<RehearsalRMIDTO> getRehearsals() throws RemoteException;
	public int reserveSeat(String studName, String OperaHouse, String OperaName) throws RemoteException;
	
	
	
}
