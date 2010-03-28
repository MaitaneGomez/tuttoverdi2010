package rehearsalServer.loginGateway;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.StringTokenizer;

import authorizationRMI.IAuthorizationRMI;
import authorizationRMI.InvalidPasswordException;
import authorizationRMI.InvalidUserException;

public class AuthRMIGateway implements IAuthorizeGateway {
	/**
	 * Add your code to invoke the Authorization RMI Server here Remember you
	 * must use the interface and exceptions contained in
	 * AuthorizationRMIClient.jar THIS SECTION BELONGS TO THE FIRST ASSIGNMENT
	 */
	
	private String serverName;
	private String ip;
	private String port;
	
	public AuthRMIGateway(String serviceUri) 
	{
		StringTokenizer st = new StringTokenizer(serviceUri);
		ip = st.nextToken();
		port = st.nextToken();
		serverName = st.nextToken();
	}

	public String login(String user, String pass) throws ValidationException 
	{
		String studentName = null;
		String name = "//" + ip + ":" + port + "/" + serverName;
		try 
		{
			IAuthorizationRMI objAuth = (IAuthorizationRMI) java.rmi.Naming.lookup(name);
			studentName = objAuth.login(user, pass);
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
		} catch (InvalidUserException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (InvalidPasswordException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return studentName;
	}
}
