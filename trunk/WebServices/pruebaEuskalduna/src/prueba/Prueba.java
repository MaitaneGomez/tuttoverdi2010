package prueba;

import euskaldunabiows.*;

public class Prueba {
	
	public static void main(String [] args)
	{
		EuskaldunaBioWSStub stub = new EuskaldunaBioWSStub("http://localhost:8080/axis2/services/EuskaldunaBioWS");
		EuskaldunaBioWSStub.RehearsalDTO[] array = stub.getRehearsals();
	}

}
