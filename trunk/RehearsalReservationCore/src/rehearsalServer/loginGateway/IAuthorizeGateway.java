package rehearsalServer.loginGateway;

import java.rmi.Remote;

//THIS INTERFACE DECLARES THE METHODS AUTHORIZATION GATEWAYS ARE GOING
//TO OFFER

public interface IAuthorizeGateway extends Remote{
	public String login(String user, String pass) throws ValidationException;
	public void initializeParameters(String [] args); //Metodo creado por nosotros para poder pasar los argumentos 
}
