package exemplo1Fila;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ConsumerFila {
    private static String url = ActiveMQConnection.DEFAULT_BROKER_URL; // "vm://localhost";
    //Nome da fila ou topico para onde enviaremos a mensagem
    private static String subject = "MY_FILA";
    private static void consumir() throws JMSException {
        // cria a fabrica de conexao e inicia a conexao
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        // Cria sessão para consumir a mensagem
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // Cria a fila destino (Queue)
        Destination destination = session.createQueue(subject);
        // Cria uma MessageConsumer da Session do Topic or Queue
        MessageConsumer consumer = session.createConsumer(destination);
        Message message = consumer.receive(15000);

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            System.out.println("Recebi da Fila a msg = '" + textMessage.getText() + "'");
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
