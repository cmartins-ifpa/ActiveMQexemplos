package exemplo2Topico;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerTopico {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; // "vm://localhost";
    private static String subject = "MEU_TOPICO"; //Nome do topico para onde enviaremos a mensagem
    private static void consumir() throws JMSException {
        // cria a fabrica de conexao e inicia a conexao
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // Cria sessão para consumir a mensagem
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Cria o destino (Topic ou Queue)
        Destination destination = session.createTopic(subject);
        // Cria uma MessageConsumer da Session do Topic or Queue
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive(15000);

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Recebi '" + textMessage.getText() + "'");
        } else {
            System.out.println("** SEM MENSAGENS **  ");
        }
        consumer.close();
        session.close();
        connection.close();
    }

    public static void main(String[] args) throws JMSException {
        while (true) {
            consumir();
        }
    }

}
