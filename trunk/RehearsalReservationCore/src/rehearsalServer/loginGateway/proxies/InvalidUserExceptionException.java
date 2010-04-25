
/**
 * InvalidUserExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

package rehearsalServer.loginGateway.proxies;

public class InvalidUserExceptionException extends java.lang.Exception{
    
    private rehearsalServer.loginGateway.proxies.AuthorizationWSStub.InvalidUserExceptionE faultMessage;

    
        public InvalidUserExceptionException() {
            super("InvalidUserExceptionException");
        }

        public InvalidUserExceptionException(java.lang.String s) {
           super(s);
        }

        public InvalidUserExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InvalidUserExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(rehearsalServer.loginGateway.proxies.AuthorizationWSStub.InvalidUserExceptionE msg){
       faultMessage = msg;
    }
    
    public rehearsalServer.loginGateway.proxies.AuthorizationWSStub.InvalidUserExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    