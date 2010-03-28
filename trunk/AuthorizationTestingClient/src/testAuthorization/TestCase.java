package testAuthorization;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import authorizationRMI.IAuthorizationRMI;
import authorizationRMI.InvalidPasswordException;
import authorizationRMI.InvalidUserException;

public class TestCase {
	private IAuthorizationRMI iauth;

	/** Creates a new instance of Main */
	public TestCase(String[] args) throws RemoteException 
	{
		getAuthManager(args[0], args[1], args[2]);
		try 
		{
			String name = iauth.login("stud1", "1111");
			System.out.println("The name of the student is: " + name);
		}
		catch (InvalidUserException e)
		{
			System.out.println("Invalid Username Exception");
		} 
		catch (InvalidPasswordException e) 
		{
			System.out.println("Invalid Password Exception");
		}
	}

	private void getAuthManager(String host, String port, String nam) 
	{
		if (System.getSecurityManager() == null) 
		{
			System.setSecurityManager(new RMISecurityManager());
		}
		
		try 
		{
			String name = "//" + host + ":" + port + "/" + nam + "";
			iauth = (IAuthorizationRMI) Naming.lookup(name);

		} catch (Exception e) 
		{
			System.err.println("Error in Authorization Manager.  getAuthManager(): " + e.getMessage());
		}
	}

	public static void main(String[] args) 
	{
		try 
		{
			new TestCase(args);
		} 
		
		catch (Exception e) 
		{
			System.out.println("Error: " + e.getMessage());
		}
	}

}
