package authorizationClient;

import authorizationClient.proxies.*;

//THIS CLASS USED TO PROVE THE WEB SERVICE AUTHORIZATION
//THAT HAS ONLY ONE METHOD, LOGIN. TO INVOKE IT WE NEED TO 
//OBTAIN A STUB GIVEN THE URL WHERE THE WS IS DEPLOY

public class Client {
	/** Creates a new instance of Client */
	public Client(String url) {
		try {
			//WE OBTAIN A STUB
			AuthorizationWSStub stub = new AuthorizationWSStub(url);
			String s = stub.login("stud2", "2222"); //INVOCATION
			System.out.println("Student " + s);
		} catch (Exception e) {
			System.out.println("Exception Type: " + e.getClass().getSimpleName());
			if (e.getMessage().contains("[InvalidUserException]")) {
				System.out.println("*** NON VALID STUDENT ***");
				System.out.println(e.getMessage());
			}

			if (e.getMessage().contains("[InvalidPasswordException]")) {
				System.out.println("*** INVALID PASSWORD ***");
				System.out.println(e.getMessage());
			}
		}
	}

	public static void main(String[] args) {
		new Client(args[0]); //ARGS[0] IS THE URL OF THE WEB SERVICE
	}
}