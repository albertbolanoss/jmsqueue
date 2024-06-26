package org.example;

import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Consumer implements Runnable {
    private static final Logger logger = LogManager.getLogger(Consumer.class);

    @Override
    public void run() {
        try {
            subscribe();
        } catch (JMSException exception) {
            logger.error("Consumer JMSException: {}", exception.getMessage(), exception);
        }
    }

    public void subscribe() throws JMSException {
        Connection connection = null;
        MessageConsumer consumer = null;
        try {
            connection = ActiveMQConfig.connect();

            Session session = ActiveMQConfig.createSession(connection);
            consumer = ActiveMQConfig.createConsumer(session, ActiveMQConfig.TOPIC_NAME);

            connection.start();


            while (!Thread.currentThread().isInterrupted()) {
                Message message = consumer.receive();

                if (message instanceof TextMessage textMessage) {
                    logger.info("Received: {}", textMessage.getText());
                } else {
                    logger.warn("Received non-text message");
                }
            }

        } catch (JMSException exception) {
            logger.error("Consumer JMSException: {}", exception.getMessage(), exception);
        } finally {
            assert consumer != null;
            consumer.close();

            assert connection != null;
            connection.close();
        }
    }
}
