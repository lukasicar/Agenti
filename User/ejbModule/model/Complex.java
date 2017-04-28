package model;

import java.io.Serializable;
import java.util.UUID;

public class Complex implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	private String type;
	private UUID d;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public UUID getD() {
		return d;
	}
	public void setD(UUID d) {
		this.d = d;
	}
	
	
}
