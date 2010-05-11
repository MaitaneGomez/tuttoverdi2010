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

//THIS CLASS IMPLEMENTS A GATEWAY THAT IS GOING TO RETRIEVE A CORBA
//OBJECT FROM THE NAMESERVICE (MILANO OR NAPOLI) IN ORTHER TO INVOKE
//ITS REMOTE METHOD (GETREHEARSALS) JUST TO DO NOT DO IT DIRECTLY IN THE SERVER

public class CorbaHouseGateway implements IOperaHGateway {
	
	//WE NEED THIS ATTRIBUTES TO LOOK FOR A CORBA OBJECT
	//IN THE NAMESERVICE WHICH HAS THE SAME
	private String serverName;
	private String ip;
	private String port;
	
	//CONSTRUCTOR
	//THIS METHOD HAS A PARAMETER, SERVICEURI, THAT CONTAINS 
	//ALL THE INFORMATION NEEDED TO LOOK FOR A CORBA OBJECT
	//IP + PORT + NAME (ATTRIBUTES)
	public CorbaHouseGateway(String serviceUri)
	{
		StringTokenizer st = new StringTokenizer(serviceUri);
		ip = st.nextToken();
		port = st.nextToken();
		serverName = st.nextToken();
	}

	//THIS METHOD LOOK FOR THE CORBA OBJECT AND CALLS ITS METHOD
	//(GETREHEARSALS) AND TRANSFORM THEM
	public List<RehearsalDO> getRehearsals() {
		List<RehearsalDO> result = new ArrayList<RehearsalDO>();
		
		String[] orb_args = {"-ORBInitialHost" , ip , "-ORBInitialPort" , port};
		ORB orb = ORB.init(orb_args, null);
		try
		{
		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
		NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
		//WE OBTAIN THE CORBA OBJECT THAT WAS STORED IN THE NAMESERVICE
		//PREVIOUSLY
		ICorbaServer objServer = ICorbaServerHelper.narrow(ncRef.resolve_str(serverName));
		//OBTAIN THE REHEARSALS CALLING THE REMOTE METHOD
		corbaServerRehearsalDTO [] rehearsals = objServer.getRehearsals();
		
		//TRANSFORM FROM CORBASERVERREHEARSALDTO TO REHEARSALDO
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

	//THIS METHOD IS USED TO KNOW EXCATLY THE NAME OF THE SERVER THE GATEWAY
	//IS ACCESSING TO, IN ORDER TO HAVE THE SAME NAME IN THE CACHE
	public String getServerName() {
		return serverName;
	}

}
