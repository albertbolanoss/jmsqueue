package org.example;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.Topic;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActiveMQConfig {
    public static final String BROKER_URL = "tcp://localhost:61616";
    public static final String TOPIC_NAME = "ExampleTopic";
    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin";

    public ActiveMQConfig() { }

    public static Connection connect() throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory(BROKER_URL);
        return factory.createConnection(USER_NAME, PASSWORD);
    }

    public static Session createSession(Connection connection) throws JMSException {
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public static MessageProducer createProducer(Session session, String topicName) throws JMSException {
        Topic topic = session.createTopic(topicName);
        return session.createProducer(topic);
    }

    public static MessageConsumer createConsumer(Session session, String topicName) throws JMSException {
        Topic topic = session.createTopic(topicName);
        return session.createConsumer(topic);
    }

}
