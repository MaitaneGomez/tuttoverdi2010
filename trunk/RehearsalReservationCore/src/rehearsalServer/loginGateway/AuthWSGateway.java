package rehearsalServer.loginGateway;

import rehearsalServer.loginGateway.proxies.*;

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

	public String login(String user, String pass) throws ValidationException {
		String studentName = null;
		
        try {
        	AuthorizationWSStub stub = new AuthorizationWSStub(authWSURL);
            studentName = stub.login(user,pass);    
        }
        catch (Exception e) {
			throw new ValidationException(e.getMessage());
		}
		
		return studentName;
	}

	@Override
	public void initializeParameters(String[] args) {
		// TODO Auto-generated method stub
		
		authWSURL = args[5];
		
	}

	
}
