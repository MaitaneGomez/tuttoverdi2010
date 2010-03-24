package rehearsalServer.loginGateway;

/**
 * Singleton and Factory Class. Given a string "rmi" or "ws", it returns an
 * object of the corresponding gateway, both of which implement the same
 * interface.
 */


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
	
	private static AuthorizationGatewayFactory factoryInstance() 
	{
		return new AuthorizationGatewayFactory();
	}

	public IAuthorizeGateway getAuthGateway(String serviceUri, String serviceTech) 
	{
		if (serviceTech.equals("rmi")) 
		{
			return new AuthRMIGateway(serviceUri);
		}
		else if (serviceTech.equals("ws")) 
			 {
				return new AuthWSGateway(serviceUri);
			 } 
			 else 
			 {
				 return null;
			 }
	}
}
