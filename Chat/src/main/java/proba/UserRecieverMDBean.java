package proba;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.websocket.Session;
import javax.jms.ObjectMessage;

import model.Complex;

import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;


@MessageDriven(

        activationConfig = {

                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),

                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),

                @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/DLQ")

        }

)
public class UserRecieverMDBean implements MessageListener{

	@EJB
	private SessionStorageLocal sessionStorage;
	
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		
		Object object;
		try {
			object = ((ObjectMessage) message).getObject();
			 if ((object instanceof Complex)) {       	
		        	Complex c=(Complex) object;	        	
		        	Session s=sessionStorage.getSession(c.getD());
		        	if(c.getType().equals("dobarLogin")){
		        		try {
							s.getBasicRemote().sendText("true");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}else{
		        		try {
							s.getBasicRemote().sendText("false");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	}
		        		
		        }
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       
		
		
		
	}

}
