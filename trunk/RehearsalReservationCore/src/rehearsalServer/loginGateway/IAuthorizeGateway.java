package rehearsalServer.loginGateway;

public interface IAuthorizeGateway {
	public String login(String user, String pass) throws ValidationException;
}
