package rehearsalServer.loginGateway;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import authorizationRMI.IAuthorizationRMI;
import authorizationRMI.InvalidPasswordException;
import authorizationRMI.InvalidUserException;

public class AuthRMIGateway implements IAuthorizeGateway {
	/**
	 * Add your code to invoke the Authorization RMI Server here Remember you
	 * must use the interface and exceptions contained in
	 * AuthorizationRMIClient.jar THIS SECTION BELONGS TO THE FIRST ASSIGNMENT
	 */
	
	//hemos creado una especie de puente (bridge) para poder pasar los args al metodo login,
	//por lo que nos creamos un metodo qeu nos devuleve una instancia del objeto
	//rmi y lo guarda para invocaciones del metodo login

	private IAuthorizationRMI bridge = null;

	public void bridge(String [] args){
		
		String name= "//" + args[3] + ":" + args[4] + "/" + args[5];
		
		
		
		if (System.getSecurityManager() == null) 
		{
			System.setSecurityManager(new RMISecurityManager());
		}
			
		try 
		{
			System.out.println(name);	
			bridge = (IAuthorizationRMI) java.rmi.Naming.lookup(name);

		} 
		catch (Exception e) 
		{
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
		
	}


	public String login(String user, String pass) throws ValidationException
	{
		String studentName = null;
		if (System.getSecurityManager() == null) 
		{
			System.setSecurityManager(new RMISecurityManager());
		}
		
		String name = "//127.0.0.1:1099/AuthorizationService";
		try 
		{	
			IAuthorizationRMI objServer = (IAuthorizationRMI) java.rmi.Naming.lookup(name);
			studentName = objServer.login(user, pass);
		} 
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (NotBoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return studentName;
		
	}
		
}
