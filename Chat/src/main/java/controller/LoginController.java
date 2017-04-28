package controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import model.Podaci;
import model.User;
import proba.UserAppJmsLocal;


@Path("/login")
public class LoginController {

	
	@EJB
	private UserAppJmsLocal userAppJmsBean;
	
	private HashMap<String, User> korisnici=Podaci.getKorisnici();
	public static ArrayList<User> logovani=new ArrayList<User>();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean uloguj(@Context HttpServletRequest request,User user){
		
		User u=korisnici.get(user.getUsername());
		//System.out.println(u);
		//System.out.println(user.getUsername());
		
		//System.out.println("zakaj");
		if(u!=null){
			if(!(u.getPassword().equals(user.getPassword())))
				return false;
			//System.out.println("logovan");
			request.getSession().setAttribute("user",u);
			logovani.add(u);
			System.out.println(logovani.size());
			return true;
		}else 
			return false;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean registruj(@Context HttpServletRequest request,User user){
		
		User u=korisnici.get(user.getUsername());
		if(u!=null){
			//System.out.println("ne moze se registrovati postojeci korisnik");
			return false;
		}else{
			//System.out.println("registovan novi user "+user.getUsername());
			korisnici.put(user.getUsername(), user);
			Podaci.save();
			return true;
		}
			
	}
	
	@GET
	@Path("/ll")
	@Produces(MediaType.TEXT_PLAIN)
	public String vjezba(){
		System.out.println("get metod");
		
		User u=new User();
		u.setUsername("admin");
		u.setPassword("admin");
		
		//userAppJmsBean.login(u);
		return " mula";			
	}
	
	
	@GET
	public boolean logOut(@Context HttpServletRequest request){
		//System.out.println("udjes li sreco");
		logovani.remove(request.getSession().getAttribute("user"));
		//System.out.println(logovani.size());
		request.getSession().invalidate();
		
		//System.out.println(request.getSession().getAttribute("user"));
		return true;
	}
	
	@GET
	@Path("/checkRights")
	@Produces(MediaType.APPLICATION_JSON)
	public User checkRights(@Context HttpServletRequest request) {
		try {
			User user = ((User) request.getSession().getAttribute("user"));
			return user;
		} catch (Exception e) {
			return null;
		}
	}
	
	@GET
	@Path("/getLoggedUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<User> getLoggedUsers(@Context HttpServletRequest request) {
		return logovani;
	}
	
	
	public static User getUser(String s){
		for(User u : logovani){
			if(u.getUsername().equals(s))
				return u;
		}
		return null;
	}
}
