/**
 * THIS OBJECT IS MEANT TO BE THE 'SERVER SIDE' OF THE CLIENT
 * IN OTHER WORDS, IT IS SUPPOSED TO RECEIVE INVOCATIONS FROM THE RMI SERVER
 * So it behaves as a REMOTE OBSERVER
 * TO BE COMPLETED BY THE STUDENTS
 */
package RMIClient;

import util.observer.rmi.RemoteObserver;

import java.rmi.RemoteException;

public class RehearsalRemoteObserver extends RemoteObserver {
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new instance of RemoteObserver ADD AS MUCH INFORMATION AS
	 * NEEDED
	 */

	public RehearsalRemoteObserver() throws RemoteException {

	}

	public void stop() {

	}

	public void update(Object arg) throws java.rmi.RemoteException {

	}
}
