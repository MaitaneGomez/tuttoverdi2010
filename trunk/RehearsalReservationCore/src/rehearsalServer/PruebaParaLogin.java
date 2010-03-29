package rehearsalServer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import authorizationRMI.IAuthorizationRMI;
import authorizationRMI.InvalidPasswordException;
import authorizationRMI.InvalidUserException;

import rehearsalServer.loginGateway.ValidationException;

public class PruebaParaLogin {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			IAuthorizationRMI objAuth = (IAuthorizationRMI) java.rmi.Naming.lookup("//127.0.0.1:1099/AuthorizationService");
			String studentName = objAuth.login("stud1", "1111");
			System.out.println(studentName);
		} 
		
		catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
