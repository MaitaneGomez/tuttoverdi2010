package rehearsalServer.houseGateway;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import corbaServer.corba.ICorbaServer;
import corbaServer.corba.ICorbaServerHelper;
import corbaServer.corba.corbaServerRehearsalDTO;

public class CorbaHouseGateway implements IOperaHGateway {
	
	private String serverName;
	private String ip;
	private String port;
	
	public CorbaHouseGateway(String serviceUri)
	{
		StringTokenizer st = new StringTokenizer(serviceUri);
		ip = st.nextToken();
		port = st.nextToken();
		serverName = st.nextToken();
	}

	public List<RehearsalDO> getRehearsals() {
		List<RehearsalDO> result = new ArrayList<RehearsalDO>();
		
		String[] orb_args = {"-ORBInitialHost" , ip , "-ORBInitialPort" , port};
		ORB orb = ORB.init(orb_args, null);
		try
		{
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		ICorbaServer objServer = ICorbaServerHelper.narrow(ncRef.resolve_str(serverName));
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
