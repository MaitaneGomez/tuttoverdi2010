package rehearsalServer;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

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
			
			List<RehearsalRMIDTO> lista = rehearsalServer.getRehearsals();
			for(int i=0; i<lista.size();i++)
			{
				System.out.println(lista.get(i).getOperaHouse() + " "  + lista.get(i).getOperaName() + " " + lista.get(i).getDate() + " " + lista.get(i).getAvailableSeats());
			}
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
