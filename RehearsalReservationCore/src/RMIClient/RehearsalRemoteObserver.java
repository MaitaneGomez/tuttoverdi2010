/**
 * THIS OBJECT IS MEANT TO BE THE 'SERVER SIDE' OF THE CLIENT
 * IN OTHER WORDS, IT IS SUPPOSED TO RECEIVE INVOCATIONS FROM THE RMI SERVER
 * So it behaves as a REMOTE OBSERVER
 * TO BE COMPLETED BY THE STUDENTS
 */
package RMIClient;

import rehearsalServer.IOperaRehearsalServer;
import rehearsalServer.RehearsalRMIDTO;
import util.observer.rmi.RemoteObserver;

import java.rmi.RemoteException;

public class RehearsalRemoteObserver extends RemoteObserver {
	private static final long serialVersionUID = 1L;
	
	private IOperaRehearsalServer server;
	private RehearsalController controller;

	/**
	 * Creates a new instance of RemoteObserver ADD AS MUCH INFORMATION AS
	 * NEEDED
	 */

	//CALLED FROM THE CONTROLLER IN ORDER TO ADD A REOMTE OBSERVER
	public RehearsalRemoteObserver(IOperaRehearsalServer serv, RehearsalController controller) throws RemoteException 
	{
		super();
		this.server = serv;
		this.controller = controller;
		this.server.addRemoteObserver(this);
		
	}

	public void stop() 
	{
		try 
		{
			this.server.deleteRemoteObserver(this);
		} 
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Object arg) throws java.rmi.RemoteException 
	{
		if(arg instanceof RehearsalRMIDTO)
		{
			this.controller.updateRehearsal((RehearsalRMIDTO) arg);
		}
	}
}
