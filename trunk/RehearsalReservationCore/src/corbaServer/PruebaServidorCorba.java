package corbaServer;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import corbaServer.corba.ICorbaServer;
import corbaServer.corba.ICorbaServerHelper;
import corbaServer.corba.corbaServerRehearsalDTO;

public class PruebaServidorCorba {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String[] orb_args = {"-ORBInitialHost" , "127.0.0.1" , "-ORBInitialPort" , "900"};
		ORB orb = ORB.init(orb_args, null);
		try
		{
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		String name = "ScalaMILANO";
		ICorbaServer objServer = ICorbaServerHelper.narrow(ncRef.resolve_str(name));
		corbaServerRehearsalDTO [] rehearsals = objServer.getRehearsals();
		
		for(int i = 0 ; i < rehearsals.length ; i++)
		{
			System.out.println(rehearsals[i].operaName + " " + rehearsals[i].date + " " + rehearsals[i].seats);
		}
		
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e);
		}
	}

}
