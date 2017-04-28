package desavanja;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;

import model.Complex;

public abstract class ObjectMessageSender {

    protected void sendObject(Complex object) throws JMSException {

        Connection connection = getConnectionFactory().createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        MessageProducer producer = session.createProducer(getQueue());



        ObjectMessage objectMessage = session.createObjectMessage();

        objectMessage.setObject(object);

        producer.send(objectMessage);



        producer.close();

        session.close();

        connection.close();

    }



    protected abstract ConnectionFactory getConnectionFactory();

    protected abstract Queue getQueue();



}