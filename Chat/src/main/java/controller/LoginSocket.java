package controller;

import java.io.IOException;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import model.Complex;
import proba.SessionStorageLocal;
import proba.UserAppJmsLocal;

@ServerEndpoint("/login")
@Singleton
public class LoginSocket {

	@EJB
	private UserAppJmsLocal userAppJmsBean;
	
	@EJB
	private SessionStorageLocal sessionStorage;
	
	@OnOpen 
	public void onOpen(Session session){ 
		/*try {
			session.getBasicRemote().sendText("Hello!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
	}
	@OnMessage 
	public void onMessage(String message, Session session){
		ObjectMapper om=new ObjectMapper();
		try {
			Complex complex=om.readValue(message, Complex.class);
			UUID znakic=UUID.randomUUID();
			if(complex.getType().equals("login")){
				complex.setD(znakic);
				userAppJmsBean.login(complex);
				sessionStorage.addMessage(znakic, session);
			}
			//session.getBasicRemote().sendText("true");
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@OnClose 
	public void onClose(Session session){ 
		
	}
	
}
