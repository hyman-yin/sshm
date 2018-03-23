package hyman.study.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {  
    public static void main(String[] args) {  
    	ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    	Connection connection = null;
    	
    	try {
			connection = connectionFactory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue("firstQueue");
			Destination destination2 = session.createQueue("secondQueue");
			Destination destination3 = session.createQueue("thirdQueue");
			
			MessageConsumer consumer = session.createConsumer(destination);
			MessageConsumer consumer2 = session.createConsumer(destination2);
			MessageConsumer consumer3 = session.createConsumer(destination3);
			
			Message message = consumer.receive();
			Message message2 = consumer2.receive();
			Message message3 = consumer3.receive();
			TextMessage tMessage = (TextMessage) message;
			TextMessage tMessage2 = (TextMessage) message2;
			TextMessage tMessage3 = (TextMessage) message3;
			
			
			System.out.println("name1: "+message.getStringProperty("name"));
			System.out.println("text1: "+tMessage.getText());

			System.out.println("name2: "+message2.getStringProperty("name"));
			System.out.println("text2: "+tMessage2.getText());
			
			System.out.println("name3: "+message3.getStringProperty("name"));
			System.out.println("text3: "+tMessage3.getText());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
    }
}  
