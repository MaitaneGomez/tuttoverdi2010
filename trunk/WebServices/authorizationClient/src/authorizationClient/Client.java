package authorizationClient;

import authorizationClient.proxies.*;

public class Client {
	/** Creates a new instance of Client */
	public Client(String url) {
		try {
			AuthorizationWSStub stub = new AuthorizationWSStub(url);
			String s = stub.login("stud1", "1111");
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
		new Client(args[0]);
	}
}