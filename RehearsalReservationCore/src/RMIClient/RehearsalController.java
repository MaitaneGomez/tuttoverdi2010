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

//THE AIM OF THIS CLASS IS TO OFFER FUNCTIONALITY TO THE 
//ACTIONS THAT TAKE PLACE IN THE GUI.
//IT CALLS THE REMOTE METHODS OF THE RESERVATION SERVER
//*********IT IS THE ONE THAT IS GOING TO LAUNCH ALL THE SYSTEM

public class RehearsalController {
	// Some data you may need
	// Feel free to add what you need
	private static IOperaRehearsalServer server; //SERVER TO CALL THE REMOTE METHODS
	private LocalObservable observable; 
	private RehearsalRemoteObserver observer;

	// CLIENT SESSION STATE - state management field.
	private String stuName;

	/**
	 * Creates a new instance of ReservationController
	 * 
	 * @throws RemoteException
	 */
	
	//CONSTRUCTOR, INITIALIZE THE OBSERVABLE AND THE OBSERVER (SERVER, CONTROLLER)
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

	//INVOCATION TO THE REMOTE METHOD
	public String login(String user, String pass)throws ValidationException, RemoteException {

		stuName = server.login(user, pass);
		return stuName;
	}

	
	//INVOCATION TO THE REMOTE METHOD
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

	
	//INVOCATION TO THE REMOTE METHOD
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
		observer.stop(); //STOPS THE OBSERVER BECAUSE IT IS GOING TO END THE SESSION
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
	
	//MAIN FUNCTION
	public static void main(String[] args) throws RemoteException {
		// TODO code application logic here
		
		
		//WE HAVE TO LOOK FOR A SERVER WITH THE PARAMETERS THAT CAME FROM
		//THE BUILD.XML FILE
		//ARGS[0]:IP  ARGS[1]:PORT   ARGS[2]:NAME
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		RMIServiceLocator servLocator = new RMIServiceLocator();
		server = servLocator.getService(name); //THE SERVICE LOCATOR IS THE ONE THAT 
		//IS GOING TO LOOK FOR THE SERVER
		new RehearsalController();//WE INVOKE THE CONSTRUCTOR
		//TO INIZIALATE THE OBSERVER AND OBSERVABLE AND CREATE THE GUI
	}
}
