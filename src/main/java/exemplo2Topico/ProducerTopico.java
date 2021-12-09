package exemplo2Topico;
import java.util.Date;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ProducerTopico {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; // "vm://localhost";
    private static String subject = "MEU_TOPICO"; //Nome do topico para onde enviaremos a mensagem
    private static void envia() throws JMSException {
        // Obtem uma conexão com o servidor JMS
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Mensagens JMS são enviadas usando uma sessão. Nós criaremos aqui uma sessão não transacional, 
        // informando false para o primeiro parametro 
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        //Destination é o topico para onde as mensagens serão enviadas. 
        // Se não existir,  ela sera criada automaticamente.
        Destination destination = session.createTopic(subject);
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage("HELLO WORLD - msg em topico enviada as "
                + new Date().toLocaleString());
        // envia a mensagem
        producer.send(message);
        System.out.println("Mensagem enviada!");
        connection.close();
    }

    public static void main(String[] args) throws JMSException {
        envia();
    }
}
