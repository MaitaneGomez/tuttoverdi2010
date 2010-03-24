package rehearsalServer.loginGateway;

public class AuthRMIGateway implements IAuthorizeGateway {
	/**
	 * Add your code to invoke the Authorization RMI Server here Remember you
	 * must use the interface and exceptions contained in
	 * AuthorizationRMIClient.jar THIS SECTION BELONGS TO THE FIRST ASSIGNMENT
	 */
	
	private String server;
	
	public AuthRMIGateway(String serviceUri) 
	{
		server = serviceUri;
	}

	public String login(String user, String pass) throws ValidationException 
	{
		String studentName = null;
		
		return studentName;
	}
}
