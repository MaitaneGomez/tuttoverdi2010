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

//En esta clase creamos un objeto CORBA y lo metemos en el NameService
//atencion el nombre del objeto a registrar debe ser el mismo que el de la base de datos

public class CorbaOperaHouse {

	public static void main(String[] args) {
		
		//Nos creamos un objeto ORB para manejar todo lo siguiente
		String [] orb_args = {"-ORBInitialHost" , args[0] , "-ORBInitialPort" , args[1]};
		ORB orb = ORB.init(orb_args, null);
		
		try
		{
			//Obtener una referencia al POA
			org.omg.CORBA.Object reference = orb.resolve_initial_references("RootPOA");
			
			//Convertir el reference en una referencia especifica
			POA poa = POAHelper.narrow(reference);
			
			//Activar el POAMAnager
			poa.the_POAManager().activate();
			
			//Crear un objeto CORBA (Llamaremos al constructor del servidor (impCorbaServer))
			impCorbaServer server = new impCorbaServer(args[2]);
			
			//Vincular el objeto CORBA con el POA
			//Registrar el objeto CORBA en el POA
			org.omg.CORBA.Object ref = poa.servant_to_reference(server);
			//Convertir la referencia a una especifica
			ICorbaServer operaRef = ICorbaServerHelper.narrow(ref);
			
			//Registrar el objeto CORBA en el NameService
			//Obtener una referencia al NaemService
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			//Transformamos la refenrecia a una especifica
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			
			//Ponerle un nombre al objeto a registrar
			NameComponent [] path = ncRef.to_name(args[2]);
			
			//Registro
			System.out.println("Initizaling Corba Server...");
			ncRef.rebind(path, operaRef);
			
			//Dejar el proceso servidor esperando peticiones
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
