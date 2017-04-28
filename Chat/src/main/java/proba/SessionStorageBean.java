package proba;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.Singleton;
import javax.websocket.Session;

@Singleton
public class SessionStorageBean implements SessionStorageLocal{
	
	private Map<UUID, Session> messageObjects = new HashMap<UUID, Session>();

	public void addMessage(UUID d, Session object) {
		// TODO Auto-generated method stub
		messageObjects.put(d, object);
	}

	public Session getSession(UUID d) {
		// TODO Auto-generated method stub
		return messageObjects.get(d);
	}

	public Session removeSession(UUID d) {
		// TODO Auto-generated method stub
		return messageObjects.remove(d);
	}
	
	
	

}
