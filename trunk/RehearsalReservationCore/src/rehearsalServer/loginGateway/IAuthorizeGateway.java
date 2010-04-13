package rehearsalServer.loginGateway;

import java.rmi.Remote;

public interface IAuthorizeGateway extends Remote{
	public String login(String user, String pass) throws ValidationException;
	public void initializeParameters(String [] args); //Metodo creado por nosotros para poder pasar los argumentos 
}
