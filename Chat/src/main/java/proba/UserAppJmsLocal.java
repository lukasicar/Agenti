package proba;

import javax.ejb.Local;

import model.Complex;


@Local
public interface UserAppJmsLocal {
	//JmsMessage register(String username, String password)

            



    public void login(Complex complex);

            



    //JmsMessage logout(User user) 



    //JmsMessage getAllUsers() 
}
