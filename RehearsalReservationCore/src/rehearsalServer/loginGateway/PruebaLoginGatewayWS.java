package rehearsalServer.loginGateway;

public class PruebaLoginGatewayWS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		IAuthorizeGateway gatewayAuth = (AuthorizationGatewayFactory.getInstance()).getAuthGateway("ws");
		gatewayAuth.initializeParameters(args);
		String name = null;
		try {
			name = gatewayAuth.login("stud2", "2222");
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("El estudiante es: " + name);

	}

}
