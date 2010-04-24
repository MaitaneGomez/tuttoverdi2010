
/**
 * InvalidPasswordExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5.1  Built on : Oct 19, 2009 (10:59:00 EDT)
 */

package authorizationClient.proxies;

public class InvalidPasswordExceptionException extends java.lang.Exception{
    
    private authorizationClient.proxies.AuthorizationWSStub.InvalidPasswordExceptionE faultMessage;

    
        public InvalidPasswordExceptionException() {
            super("InvalidPasswordExceptionException");
        }

        public InvalidPasswordExceptionException(java.lang.String s) {
           super(s);
        }

        public InvalidPasswordExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public InvalidPasswordExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(authorizationClient.proxies.AuthorizationWSStub.InvalidPasswordExceptionE msg){
       faultMessage = msg;
    }
    
    public authorizationClient.proxies.AuthorizationWSStub.InvalidPasswordExceptionE getFaultMessage(){
       return faultMessage;
    }
}
    