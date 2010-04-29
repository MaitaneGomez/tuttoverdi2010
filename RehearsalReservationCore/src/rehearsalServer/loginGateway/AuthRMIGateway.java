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
	
	//Hemos creado una especie de puente (bridge) para poder pasar los args al metodo login,
	//por lo que nos creamos un metodo qeu nos devuleve una instancia del objeto
	//rmi y lo guarda para invocaciones del metodo login

	private IAuthorizationRMI objAuth = null;


	public String login(String user, String pass) throws ValidationException
	{
		String studentName = null;
		
		try 
		{	
			studentName = objAuth.login(user, pass);
		} 
		catch (RemoteException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InvalidUserException e) 
		{
			throw new ValidationException(e.getMessage());
		} 
		catch (InvalidPasswordException e) 
		{
			throw new ValidationException(e.getMessage());
		}
		
		return studentName;
		
	}


	@Override
	public void initializeParameters(String[] args) 
	{
		String name= "//" + args[0] + ":" + args[1] + "/" + args[2];
		
		if (System.getSecurityManager() == null) 
		{
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try 
		{
			objAuth = (IAuthorizationRMI) java.rmi.Naming.lookup(name);
		}
		catch (MalformedURLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
}
