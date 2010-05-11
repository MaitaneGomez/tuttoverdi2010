/**
 * CORBA SERVER PROVIDING ONE REMOTE SERVICE TO THE RMI SERVER
 * USE THE COMMAND INTERFACE TO PRINT THE TRACE OF ACTIVITIES OF THIS SERVER
 */

//aqui es donde se genera el objeto corba y se mete al nameservice
package corbaServer;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import corbaServer.corba.ICorbaServer;
import corbaServer.corba.ICorbaServerHelper;
import corbaServer.corba.impCorbaServer;


//CLASS WHERE WE CREATE A CORBA OBJECT (MILANO OR NAPOLI) AND WE REGISTER
//THEM IN THE NAMESERVICE

public class CorbaOperaHouse {

	public static void main(String[] args) {
		
		//WE CREATE AN ORB OBJECT TO DEAL WITH THE WHOLE OPERATION
		//ARGS[0] WILL BE THE IP OF THE NAMESERVICE AND ARGS[1] WILL BE
		//NAMESERVICE PORT NUMBER
		String [] orb_args = {"-ORBInitialHost" , args[0] , "-ORBInitialPort" , args[1]};
		ORB orb = ORB.init(orb_args, null);
		
		try
		{
			//WE OBTAIN A REFERENCE TO THE POA
			org.omg.CORBA.Object reference = orb.resolve_initial_references("RootPOA");
			
			//WE CONVERT THE "REFERENCE" TO A SPECIFIC REFERENCE
			POA poa = POAHelper.narrow(reference);
			
			//WE ACTIVATE THE POA MANAGER
			poa.the_POAManager().activate();
			
			//WE CREATE A CORBA OBJECT INVOKING THE CONSTRUCTOR OF THE SERVER
			//(ImpCorvaServer) AND WE PASS AS A PARAMETER THE NAME OF THE DATABASE
			//THIS OBJECT IS GOING TO ACCESS WHICH IS AT THE SAME TIME
			//THE NAME THIS OBJECT IS GOING TO BE REGISTERED WITH (ARGS[2])
			impCorbaServer server = new impCorbaServer(args[2]);
			
			//LINKING THE CORBA OBJECT WITH THE POA
			//REGISTERING THE CORBA OBJECT IN THE POA
			org.omg.CORBA.Object ref = poa.servant_to_reference(server);
			//WE CONVERT THE REFERENCE TO A SPECIFIC ONE
			ICorbaServer operaRef = ICorbaServerHelper.narrow(ref);
			
			//REGISTERING THE CORBA OBJECT IN THE NAMESERVICE
			//ONTAINING A REFERENCE TO THE NAMESERVICE
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			//WE CONVERT THE REFERENCE TO A SPECIFIC ONE
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			//WE ASIGN A NAME TO THE OBJECT WHICH IS GOING TO BE REGISTERED
			//THIS NAME IS ARGS[2]
			NameComponent [] path = ncRef.to_name(args[2]);
			
			//REGISTERING...
			System.out.println("Initizaling Corba Server...");
			ncRef.rebind(path, operaRef);
			
			//WE LEAVE THE OBJECT LISTENING TO REQUESTS
			System.out.println("Server "+ args[2]+ " working and waiting for requests...");
			orb.run();
		}
		
		catch (Exception e)
		{
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.err);
		}

	} 
}
