package epam.jms;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.NamingException;

/**
 *  JMS sends a Test Message to a Queue
 */
public class QueueProducer {
    public static void main(String[] args) throws NamingException, JMSException {
        Connection connection = null;
        try {
            System.out.println("Create JNDI Context");
            Context context = ContextUtil.getInitialContext();
             
            System.out.println("Get connection facory");
            ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
             
            System.out.println("Create connection");
            connection = connectionFactory.createConnection();
             
            System.out.println("Create session");
            Session session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
             
            System.out.println("Lookup queue");
            Queue queue = (Queue) context.lookup("/queue/queueA");
             
            System.out.println("Start connection");
            connection.start();
             
            System.out.println("Create producer");
            MessageProducer producer = session.createProducer(queue);
             
            System.out.println("Create test message");
            Message testText = session.createTextMessage("Test Message");
             

            System.out.println("Send test message");

            producer.send(testText);

        } finally {

            if (connection != null) {

                System.out.println("close the connection");

                connection.close();

            }
 

        }

 

    }

}
