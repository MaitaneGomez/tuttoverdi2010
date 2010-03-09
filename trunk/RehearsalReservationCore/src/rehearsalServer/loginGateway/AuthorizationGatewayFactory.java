package rehearsalServer.loginGateway;

/**
 * Singleton and Factory Class. Given a string "rmi" or "ws", it returns an
 * object of the corresponding gateway, both of which implement the same
 * interface.
 */

public class AuthorizationGatewayFactory {
	// Add your code here
	// THIS CLASS MUST BE A SINGLETON

	public IAuthorizeGateway getAuthGateway(String serviceUri,
			String serviceTech) {
		if (serviceTech.equals("rmi")) {
			return new AuthRMIGateway(serviceUri);
		} else if (serviceTech.equals("ws")) {
			return new AuthWSGateway(serviceUri);
		} else {
			return null;
		}
	}
}
