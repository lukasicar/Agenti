package desavanja;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import model.Complex;
import model.Podaci;
import model.User;

@MessageDriven(

        activationConfig = {

                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),

                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),

                @ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/ExpiryQueue")

        }

)
public class JmsReciever extends ObjectMessageSender implements MessageListener{

	@Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:/jms/queue/DLQ")
    private Queue queue;
	
	private HashMap<String, User> korisnici=Podaci.getKorisnici();
	public static ArrayList<User> logovani=new ArrayList<User>();
	
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		  try {
			Object object = ((ObjectMessage)message).getObject();
			if(object instanceof Complex){
				if(((Complex) object).getType().equals("login")){
					Complex r=(Complex) object;
					User p=(r).getUser();
					User u=korisnici.get(p.getUsername());
					if(u!=null){
						if(!(u.getPassword().equals(p.getPassword()))){
							r.setType("losLogin");
							sendObject(r);
						}
						logovani.add(u);
						r.setType("dobarLogin");
						r.setUser(p);
						sendObject(r);
					}else{ 
						r.setType("losLogin");
						sendObject(r);
					}
				}
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		  
          // TODO: Refactor (visitor pattern)

		  //System.out.println(object instace of User);
	}

	@Override
	protected ConnectionFactory getConnectionFactory() {
		// TODO Auto-generated method stub
		return connectionFactory;
	}

	@Override
	protected Queue getQueue() {
		// TODO Auto-generated method stub
		return queue;
	}

	
	
}
