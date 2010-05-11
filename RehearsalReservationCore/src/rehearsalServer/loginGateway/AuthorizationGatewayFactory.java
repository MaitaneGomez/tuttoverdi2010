package rehearsalServer.loginGateway;

/**
 * Singleton and Factory Class. Given a string "rmi" or "ws", it returns an
 * object of the corresponding gateway, both of which implement the same
 * interface.
 */

//THE AIM OF THIS CLASS IS TO CREATE A GATEWAY TO INVOKE THE 
//LOGIN METHOD. IT CAN BE VIA RMI OR VIA WS. IT IS A SINGLETON

public class AuthorizationGatewayFactory {
	// Add your code here
	// THIS CLASS MUST BE A SINGLETON
	
	private static AuthorizationGatewayFactory instance = null;
	
	public static AuthorizationGatewayFactory getInstance()
	{
		if(instance == null)
		{
			instance = factoryInstance();
		}
		return instance;
	}
	
	//PRIVATE CONSTRUCTOR NOT ACCESSIBLE FOR THE USERS
	private static AuthorizationGatewayFactory factoryInstance() 
	{
		return new AuthorizationGatewayFactory();
	}

	
	//THIS METHOD CREATES A GATEWAY DEPENDING ON THE TECH (WS OR RMI)
	//THIS METHOS IS INVOKED FROM THE REHEARSALSERVER AND THE SERVICETECH
	//IS AN ARGUMET OF THE BUILD.XML
	public IAuthorizeGateway getAuthGateway(String serviceTech) 
	{
		if (serviceTech.equals("rmi")) 
		{
			return new AuthRMIGateway();
		}
		else if (serviceTech.equals("ws")) 
			 {
				return new AuthWSGateway();
			 } 
			 else 
			 {
				 return null;
			 }
	}
}
