package controller;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class Socketi {

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

			//String s1=message.split("//")[1];
			//String s2=message.split("//")[0];
			//Message m=new Message();
			//m.setFrom(LoginController.getUser(s1));
			//m.setDate(new Date());
			//m.setContent(s2);
			
			try {
				session.getBasicRemote().sendText(message);
				//session.getBasicRemote().sendObject(m);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	@OnClose 
	public void onClose(Session session){ 
		
	}

	
}
