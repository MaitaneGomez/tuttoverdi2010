package rehearsalServer.loginGateway;

import rehearsalServer.loginGateway.proxies.*;

public class Client {
	/** Creates a new instance of Client */

	public static void main(String[] args) {
		try 
		{
			AuthorizationWSStub stub = new AuthorizationWSStub(args[0]);
			String s = stub.login("stud2", "2222");
			System.out.println("Student " + s);
		} 
		catch (Exception e) 
		{
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
}
