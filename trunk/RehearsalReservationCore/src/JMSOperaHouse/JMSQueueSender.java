package JMSOperaHouse;


import javax.jms.*;
import javax.naming.*;

import JMSOperaHouse.dao.JMSOperaHouseDAO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class JMSQueueSender 
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
        QueueSender             queueSender = null;
        ObjectMessage			objectMessage = null;
        final int               NUM_MSGS;
        
        if ((args.length < 1) || (args.length > 2)) {
            System.out.println("Usage: java SimpleQueueSender <queue-name> [<number-of-messages>]");
            System.exit(1);
        }
        
        queueName = new String(args[0]);
        
        System.out.println("Queue name is " + queueName);
        
        if (args.length == 2) {
            NUM_MSGS = (new Integer(args[1])).intValue();
        } else {
            NUM_MSGS = 2;
        }
        
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
            queueSender = queueSession.createSender(queue);
            objectMessage = queueSession.createObjectMessage();
            
            
            JMSOperaHouseDAO dao = new JMSOperaHouseDAO();
            dao.connect();
            List <RehearsalJMSDTO> DTOList = dao.getRehearsals();
            dao.disconnect();
            
            for(int i=0; i<DTOList.size(); i++)
            {
            	objectMessage.setObject(DTOList.get(i));
            	System.out.println("Sending object message number: " + i+1);
            	queueSender.send(objectMessage);  
            }
            
           
            
            System.out.println("Sending null message ... end of message set");
            queueSender.send(queueSession.createMessage());
        } catch (JMSException e) {
            System.out.println("Exception occurred Sending Messages: " + e.toString());
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {
                	System.out.println("Exception occurred closing the Queue Connection: " + e.toString());                	
                }
            }
        }
    
	}

}
