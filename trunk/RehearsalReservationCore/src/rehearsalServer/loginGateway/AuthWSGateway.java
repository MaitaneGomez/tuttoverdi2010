package rehearsalServer.loginGateway;

import rehearsalServer.loginGateway.proxies.*;

//THIS CLASS INVOKE THE LOGIN METHOD THAT OFFERS THE WEB SERVICE 
//AUTHORIZATION
//TO DO THAT, WE NEED A STUB AND A URL.
// THE URL IS GIVEN BY THE BUILD.XML AND LOADED IN AN ATTRIBUTE
//BY THE INITIALIZE PARAMETERS METHOD

public class AuthWSGateway implements IAuthorizeGateway {

	/**
	 * Add your code to invoke the Authorization WS here Remember you must use
	 * the stub generated in the proxies package in order to invoke the WS. On
	 * the other hand, REMEMBER that you must catch the generic exception
	 * EXCEPTION AND SEE the contained message in order to map it to the
	 * ValidationException
	 * 
	 */
	
	private String authWSURL = "";
	
	public AuthWSGateway() {

	}

	//INVOKATION OF THE LOGIN METHOD IN THE WS
	public String login(String user, String pass) throws ValidationException {
		String studentName = null;
		
        try {
        	//THE CONSTRUCTOR OF THE STUB NEED THE URL WERE THE WS
        	//IS DEPLOYED
        	AuthorizationWSStub stub = new AuthorizationWSStub(authWSURL);
            studentName = stub.login(user,pass);    
        }
        catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
		
		return studentName;
	}

	@Override
	//METHOD THAT INITIALIZES THE AUTHWSURL ATTRIBUTE TO KNOW
	//THE URL WERE THE WS IS DEPLOYED. THIS DATA COMES FROM
	//THE BUILD.XML FILE
	public void initializeParameters(String[] args) {
		// TODO Auto-generated method stub
		
		authWSURL = args[5];
		
	}

	
}
