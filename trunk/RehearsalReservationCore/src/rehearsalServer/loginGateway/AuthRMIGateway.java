package rehearsalServer.loginGateway;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import authorizationRMI.IAuthorizationRMI;
import authorizationRMI.InvalidPasswordException;
import authorizationRMI.InvalidUserException;


//THE AIM OF THIS CLASS IS TO GICE ACCESS TO THE LOGIN METHOD IMPLEMENTED
//BY THE AUTHORIZATIONMANAGER
//WE HAVE CREATE A "BRIDGE" (INITIALIZE PARAMETERS) IN ORDER TO PASS THE
//NEEDED PARAMETERS FROM THE REHEARSALSERVER. THAT'S WHY WE HAVE CREATED
//AN ATTRIBUTE WHICH IS IN FACT A GATEWAY, JUST TO INITIALIZE IT AND USE 
//IT LATER

public class AuthRMIGateway implements IAuthorizeGateway {
	/**
	 * Add your code to invoke the Authorization RMI Server here Remember you
	 * must use the interface and exceptions contained in
	 * AuthorizationRMIClient.jar THIS SECTION BELONGS TO THE FIRST ASSIGNMENT
	 */
	
	//ATTRIBUTE WHICH REPRESENTS A GATEWAY
	private IAuthorizationRMI objAuth = null;


	//THIS METHOD INVOKES THE REMOTE ONE IN THE AUTHORIZATIONMANAGER
	//TO KNOW IF A STUDENT IS CORRECT OR NOT
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
		//EXCEPTIN THAT HAS TO BE THROWN
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
	//THIS METHOD LOOK FOR A RMI OBJECT THAT HAS THE SAME NAME WE 
	//REPRESENT HERE. THIS OBJECT IS GOING TO BE USED LATER TO INVOKE 
	//THE LOGIN METHOD
	//ARGS[0] IS IP, ARGS[1] IS PORT AND ARGS[2] IS NAME
	//THIS ARGS COME FROM THE REHEARSAL SERVER
	public void initializeParameters(String[] args) 
	{
		String name= "//" + args[0] + ":" + args[1] + "/" + args[4];
		
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
