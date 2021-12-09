package exemplo1Fila;
import java.util.Date;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerFila {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; // "vm://localhost";
    private static String subject = "MY_FILA"; //Nome da fila da mensagem
    private static void envia(String msg) throws JMSException {
    	
        // Obtem uma conexão com o servidor JMS
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Mensagens JMS são enviadas usando uma sessão. 
        // Uma sessão não transacional é informando com "false" no primeiro parametro 
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

         //Destination é a fila para onde as mensagens serão enviadas. 
        // Se não existir,  ela sera criada automaticamente.
        Destination destination = session.createQueue(subject);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage(
                  msg +subject + " em " + new Date().toLocaleString());
        producer.send(message); // envia a mensage
        System.out.println("Mensagem enviada!");
        connection.close();
    }
    public static void main(String[] args) throws JMSException {
    	String msg = "HELLO WORLD!   \n msg enviada para a fila " ;
    	if (args != null && args.length>0 ) {    		
    	   msg = args[0] + "  \n FILA = ";    	}
        envia(msg);
    }
}
