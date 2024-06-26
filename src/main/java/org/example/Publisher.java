package org.example;

import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Publisher implements Runnable {
    private static final Logger logger = LogManager.getLogger(Publisher.class);
    @Override
    public void run() {
        publish();
    }
    public void publish() {
        try {
            Connection connection = ActiveMQConfig.connect();
            connection.start();

            Session session = ActiveMQConfig.createSession(connection);

            MessageProducer producer = ActiveMQConfig.createProducer(session, ActiveMQConfig.TOPIC_NAME);
            TextMessage message = session.createTextMessage("Hello from Publisher!");

            producer.send(message);
            logger.info("Sent: {}", "ExampleTopic");

            session.close();
            connection.close();
        } catch (JMSException exception) {
            logger.error("producer JMSException: {}", exception.getMessage(), exception);
        }
    }
}
