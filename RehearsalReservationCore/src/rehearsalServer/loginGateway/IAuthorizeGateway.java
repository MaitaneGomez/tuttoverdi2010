package rehearsalServer.loginGateway;

import java.rmi.Remote;

public interface IAuthorizeGateway extends Remote{
	public String login(String user, String pass) throws ValidationException;
}
