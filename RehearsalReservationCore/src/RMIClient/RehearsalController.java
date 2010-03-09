package RMIClient;

import RMIClient.gui.RMIClientGUI;

import rehearsalServer.IOperaRehearsalServer;
import rehearsalServer.RehearsalRMIDTO;

import util.observer.local.LocalObservable;
import java.util.Observer;
import java.rmi.RemoteException;

import java.util.List;

public class RehearsalController {
	// Some data you may need
	// Feel free to add what you need
	private IOperaRehearsalServer server;
	private LocalObservable observable;
	private RehearsalRemoteObserver observer;

	// CLIENT SESSION STATE - state management field.
	private String stuName;

	/**
	 * Creates a new instance of ReservationController
	 * 
	 * @throws RemoteException
	 */
	public RehearsalController(String[] args) throws RemoteException {

		observable = new LocalObservable();

	}

	// --------------- System Events - Remote Method Invocation --------
	// TO BE COMPLETELY PROGRAMMED BY THE STUDENTS - 1st Assignment

	public String login(String user, String pass) {

		// add your code here
		return stuName;
	}

	public List<RehearsalRMIDTO> getRehearsals() {
		List<RehearsalRMIDTO> subjects = null;
		// add your code here
		return subjects;
	}

	public void reserveSeat(String operaHouse, String operaName) {
		// add your code here
	}

	// -------- Remote Observer Notification ---------------
	public void updateRehearsal(RehearsalRMIDTO reh) {
		// propagate the changed rehearsal so that the GUI can update the
		// rehearsal details

	}

	// ------------------ End of Remote Observer Notification --------
	public void exit() throws RemoteException {
		observer.stop();
		System.exit(0);
	}

	// ---------------------- Local Observable ---------------------------
	public void addLocalObserver(Observer observer) {
		observable.addObserver(observer);
	}

	public void deleteLocalObserver(Observer observer) {
		observable.deleteObserver(observer);
	}

	public void notifyLocalObservers(Object arg) {
		observable.setChanged();
		observable.notifyObservers(arg);
	}

	/**
	 * @param args
	 *            the command line arguments
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO code application logic here
		new RehearsalController(args);
	}
}
