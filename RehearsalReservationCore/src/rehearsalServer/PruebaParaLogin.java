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
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		try 
		{
			IOperaRehearsalServer rehearsalServer = (IOperaRehearsalServer) java.rmi.Naming.lookup(name);
			System.out.println("he encontrado el objeto rmi remoto...");
			String studentName = rehearsalServer.login("stud1", "1111");
			System.out.println("el nombre del estudiante es: " + studentName);
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
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
