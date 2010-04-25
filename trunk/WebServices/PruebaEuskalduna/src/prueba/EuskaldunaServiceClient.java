package prueba;

import proxies.*;


public class EuskaldunaServiceClient 
{
    public static void main(String[] args) {
     
            EuskaldunaBioStub.RehearsalDTO[] array = null;

            try {
                EuskaldunaBioStub stub = new EuskaldunaBioStub(args[0]);
               

               
					array = stub.getRehearsals();
					for(int i =0; i < array.length; i++)
						System.out.println(array[i].getOperaName() + " " + array[i].getDate() + " " + array[i].getSeats());
						//System.out.println(array.length);
                   
            } catch (Exception e) {
                System.err.println("Error ACCESSING the web service: " + e.getMessage());
            }
        }
    }
