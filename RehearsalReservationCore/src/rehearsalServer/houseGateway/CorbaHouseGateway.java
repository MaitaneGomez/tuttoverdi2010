package rehearsalServer.houseGateway;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import corbaServer.corba.ICorbaServer;
import corbaServer.corba.ICorbaServerHelper;
import corbaServer.corba.corbaServerRehearsalDTO;

public class CorbaHouseGateway implements IOperaHGateway {
	
	private String server;
	
	public CorbaHouseGateway(String nameServer)
	{
		server = nameServer;
	}

	public List<RehearsalDO> getRehearsals() {
		List<RehearsalDO> result = new ArrayList<RehearsalDO>();
		
		String[] orb_args = {"-ORBInitialHost" , "127.0.0.1" , "-ORBInitialPort" , "900"};
		ORB orb = ORB.init(orb_args, null);
		try
		{
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		ICorbaServer objServer = ICorbaServerHelper.narrow(ncRef.resolve_str(server));
		corbaServerRehearsalDTO [] rehearsals = objServer.getRehearsals();
		
		for(int i = 0 ; i < rehearsals.length ; i++)
		{
			result.add(new RehearsalDO(rehearsals[i].operaName, rehearsals[i].date, rehearsals[i].seats));
		}
		
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e);
		}

		return result;
	}
}
