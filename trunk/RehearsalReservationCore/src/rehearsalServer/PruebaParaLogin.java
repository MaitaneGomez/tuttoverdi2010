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
			String studentName = rehearsalServer.login("stud5", "maigo");
			System.out.println("el nombre del estudiante es: " + studentName);
		} 
		catch (MalformedURLException e) 
		{
			System.out.println("1");
		} 
		catch (RemoteException e) 
		{
			System.out.println("2");
		} 
		catch (NotBoundException e) 
		{
			System.out.println("3");
		} catch (ValidationException e) {
			System.out.println("4");
		}
	}

}
