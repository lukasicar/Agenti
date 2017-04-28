package proba;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Queue;

import model.Complex;


@Singleton
public class UserAppJmsBean extends ObjectMessageSender implements UserAppJmsLocal {

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/queue/ExpiryQueue")
    private Queue queue;

    @Override
    protected ConnectionFactory getConnectionFactory() {
        return connectionFactory;
    }
    @Override
    protected Queue getQueue() {
        return queue;
    }
    
	public void login(Complex complex) {
		// TODO Auto-generated method stub
		
		try {
			sendObject(complex);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
}
