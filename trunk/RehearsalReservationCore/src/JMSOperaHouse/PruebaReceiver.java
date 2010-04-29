package JMSOperaHouse;

import javax.jms.*;
import javax.naming.*;

import JMSOperaHouse.RehearsalJMSDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class PruebaReceiver 
{
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		 String                  queueName = null;
	        Context                 jndiContext = null;
	        QueueConnectionFactory  queueConnectionFactory = null;
	        QueueConnection         queueConnection = null;
	        QueueSession            queueSession = null;
	        Queue                   queue = null;
	        QueueReceiver           queueReceiver = null;
	        ObjectMessage           objectMessage = null;
	                

	        queueName = new String(args[0]);
	        System.out.println("Queue name is " + queueName);
	        
	        try {
	           	Properties props = new Properties();
	        	props.load(new FileInputStream("jndi.properties"));
	            jndiContext = new InitialContext(props);
	        } catch (NamingException e) {
	            System.out.println("Could not create JNDI API context: " + e.toString());
	            System.exit(1);
	        }catch(IOException ioe){
	            System.out.println("Could not find properties file: " + ioe.toString());
	            System.exit(1);        	
	        }

	        try {
	        	queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
	        	queue = (Queue) jndiContext.lookup(queueName);        	
	        } catch (NamingException e) {
	            System.out.println("JNDI API lookup failed: " + e.toString());
	            System.exit(1);
	        }

	        try {
	            queueConnection = queueConnectionFactory.createQueueConnection();
	            queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	            queueReceiver = queueSession.createReceiver(queue);
	            queueConnection.start();
	            while (true) {
	                Message m = queueReceiver.receive(1);
	                if (m != null) {
	                    if (m instanceof ObjectMessage) 
	                    {
	                    	objectMessage = (ObjectMessage) m;
	                    	RehearsalJMSDTO reh = (RehearsalJMSDTO) objectMessage.getObject();
	                    	System.out.println(reh.getOperaName() + " " + reh.getDate() + " " + reh.getSeats());
	                    } 
	                    else
	                    {
	                        break;
	                    }
	                }
	            }
	        } catch (JMSException e) {
	            System.out.println("Exception occurred: " + e.toString());
	        } finally {
	            if (queueConnection != null) {
	                try {
	                    queueConnection.close();
	                } catch (JMSException e) {}
	            }
	        }

	}

}
