package RMIClient;

import RMIClient.gui.RMIClientGUI;

import rehearsalServer.IOperaRehearsalServer;
import rehearsalServer.RehearsalRMIDTO;
import rehearsalServer.loginGateway.ValidationException;

import util.observer.local.LocalObservable;

import java.util.ArrayList;
import java.util.Observer;
import java.rmi.RemoteException;

import java.util.List;

public class RehearsalController {
	// Some data you may need
	// Feel free to add what you need
	private static IOperaRehearsalServer server;
	private LocalObservable observable;
	private RehearsalRemoteObserver observer;

	// CLIENT SESSION STATE - state management field.
	private String stuName;

	/**
	 * Creates a new instance of ReservationController
	 * 
	 * @throws RemoteException
	 */
	public RehearsalController(){

		observable = new LocalObservable();
		try
		{
			observer = new RehearsalRemoteObserver(server, this);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		
		//Creating the GUI...
		System.out.println("Creating the gui...");
		RMIClientGUI gui = new RMIClientGUI(this);
		gui.setVisible(true);
	}

	// --------------- System Events - Remote Method Invocation --------
	// TO BE COMPLETELY PROGRAMMED BY THE STUDENTS - 1st Assignment

	public String login(String user, String pass)throws ValidationException, RemoteException {

		stuName = server.login(user, pass);
		return stuName;
	}

	public List<RehearsalRMIDTO> getRehearsals() {
		
		List<RehearsalRMIDTO> rehearsals = new ArrayList<RehearsalRMIDTO>();
		
		try 
		{
			rehearsals = server.getRehearsals();
		} 
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// add your code here
		
		return rehearsals;
	}

	public int reserveSeat(String operaHouse, String operaName) {
		// add your code here
		
		int status=0;
		
		try 
		{
			status = server.reserveSeat(stuName, operaHouse, operaName);
		} 
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	// -------- Remote Observer Notification ---------------
	public void updateRehearsal(RehearsalRMIDTO reh) {
		// propagate the changed rehearsal so that the GUI can update the
		// rehearsal details
		
		this.notifyLocalObservers(reh);

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
		
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		RMIServiceLocator servLocator = new RMIServiceLocator();
		server = servLocator.getService(name);
		new RehearsalController();
	}
}
