package rehearsalServer.houseGateway;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import JMSOperaHouse.RehearsalJMSDTO;

//THE AIM OF THIS CLASS IS TO OBTAIN THE REHEARSALS OBJECTS THAT ARE
//STORED IN THE QUEUE. THE QUEUE HAS REHEARSALS FROM OPERALONDON

public class JMSHouseGateway implements IOperaHGateway 
{
	//ATTRIBUTES THAT ARE NEEDED IN ORDER TO ACCESS TO THE QUEUE
	private String serviceName;
	private String serviceUri;

	//CONSTRUCTOR TAHT RECIEVES SERVICEURI (IT IS THE NAME OF THE QUEUE)
	//THE SERVICEuri IS OBTAINED FROM THE XML
	public JMSHouseGateway(String serviceUri)
	{
		StringTokenizer st = new StringTokenizer(serviceUri);
		this.serviceUri = st.nextToken();
		this.serviceName = st.nextToken();
	}

	
	//METHOS THAT ACCEESSES TO THE QUEUE AND RETRIEVES THE OBJECTS
	//OF REHEARSALS IN LONDON AND TRANSFORM THEM
	public List<RehearsalDO> getRehearsals() 
	{
		String                  queueName = null;
	    Context                 jndiContext = null;
	    QueueConnectionFactory  queueConnectionFactory = null;
	    QueueConnection         queueConnection = null;
	    QueueSession            queueSession = null;
	    Queue                   queue = null;
	    QueueReceiver           queueReceiver = null;
	    ObjectMessage           objectMessage = null;
		List<RehearsalDO> result = new ArrayList<RehearsalDO>();
		
		queueName = new String(serviceUri);
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
            //OBTAINING THE OBJECTS FROM THE QUEUE
            while (true) {
                Message m = queueReceiver.receive(1);
                if (m != null) {
                    if (m instanceof ObjectMessage) 
                    {
                    	objectMessage = (ObjectMessage) m;
                    	RehearsalJMSDTO reh = (RehearsalJMSDTO) objectMessage.getObject();
                    	//TRANSFORM FROM REHEARSALJMSDTO TO REHEARSALDO
                    	RehearsalDO newRehearsalDO = new RehearsalDO(reh.getOperaName(), reh.getDate(), reh.getSeats());
                    	result.add(newRehearsalDO);
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
		
		
		return result;
	}

	@Override
	//THIS METHOD IS USED TO KNOW EXCATLY THE NAME OF THE SERVICE THE GATEWAY
	//IS ACCESSING TO, IN ORDER TO HAVE THE SAME NAME IN THE CACHE
	public String getServerName() 
	{
		return this.serviceName;
	}

}
