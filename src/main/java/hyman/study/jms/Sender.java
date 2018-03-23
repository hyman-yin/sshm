package hyman.study.jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {
	private static final String USER = ActiveMQConnectionFactory.DEFAULT_USER;
	private static final String PASSWORD = ActiveMQConnectionFactory.DEFAULT_PASSWORD;
	private static final String BROKER_URL = ActiveMQConnectionFactory.DEFAULT_BROKER_URL;
	
	public static void main(String[] args) {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USER,PASSWORD,BROKER_URL);
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			
			//是否启用事务，是否自动接受请求
			Session session =connection.createSession(false, Session.AUTO_ACKNOWLEDGE); 
			
			Destination destination = session.createQueue("firstQueue");
			Destination destination2 = session.createQueue("secondQueue");
			Destination destination3 = session.createQueue("thirdQueue");
			
			MessageProducer producer = session.createProducer(destination);
			MessageProducer producer2 = session.createProducer(destination2);
			MessageProducer producer3 = session.createProducer(destination3);
			
			//设置不持久化，重启后数据丢失
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			producer2.setDeliveryMode(DeliveryMode.PERSISTENT);
			producer3.setDeliveryMode(DeliveryMode.PERSISTENT);
			
			//定义消息对象
			TextMessage message = session.createTextMessage();
			TextMessage message2 = session.createTextMessage();
			TextMessage message3 = session.createTextMessage();
			
			message.setText("hello world!");
			message.setStringProperty("name", "hyman");

			message2.setText("hello world 222222222!");
			message2.setStringProperty("name", "hyman      222222222222");
			
			message3.setText("hello world 333333333333333333333333!");
			message3.setStringProperty("name", "hyman        3333333333333333333333");
			
			//发送消息到broker
			producer.send(message);
			producer2.send(message2);
			producer3.send(message3);
			
		} catch (JMSException e) {
			System.out.println("create connection failed....");
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (JMSException e) {
				System.out.println("close session failed....");
				e.printStackTrace();
			}
		}
	}	
}