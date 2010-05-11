package RMIClient;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import rehearsalServer.IOperaRehearsalServer;


//THIS CLASS IS GOING TO LOOK FOR A RMI oBJECT IN THE RMIREGISTRY
//THE RMI OBJECT IS THE REHEARSALS SERVER
public class RMIServiceLocator 
{
	private IOperaRehearsalServer service;
	
	public IOperaRehearsalServer getService(String name) 
	{
		try 
		{
			service = (IOperaRehearsalServer) java.rmi.Naming.lookup(name);
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
		}
		return service;
	}
}
