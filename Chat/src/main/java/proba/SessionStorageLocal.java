package proba;

import java.util.UUID;

import javax.ejb.Local;
import javax.websocket.Session;

@Local
public interface SessionStorageLocal {

	
	
	void addMessage(UUID d, Session object);

    Session getSession(UUID d);

    Session removeSession(UUID d);
	
}
