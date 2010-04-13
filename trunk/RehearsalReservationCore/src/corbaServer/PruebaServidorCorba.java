package corbaServer;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import corbaServer.corba.ICorbaServer;
import corbaServer.corba.ICorbaServerHelper;
import corbaServer.corba.corbaServerRehearsalDTO;

//Esta es la prueba para Corba, ya no la necesitamos
public class PruebaServidorCorba {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String[] orb_args = {"-ORBInitialHost" , args[0] , "-ORBInitialPort" , args[1]};
		ORB orb = ORB.init(orb_args, null);
		try
		{
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		ICorbaServer objServer = ICorbaServerHelper.narrow(ncRef.resolve_str(args[2]));
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
