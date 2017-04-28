package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Podaci {

	private static HashMap<String, User> korisnici = null;
	
	@SuppressWarnings("unchecked")
	public static HashMap<String, User> getKorisnici() {
		if (korisnici == null) {
			try {
				FileInputStream fin = new FileInputStream("korisnici.bin");
				ObjectInputStream ois = new ObjectInputStream(fin);
				korisnici = (HashMap<String, User>) ois.readObject();
				ois.close();
			} catch (Exception e) {
				System.out.println("greska pri ucitavanju korisnika");
				korisnici = new HashMap<String, User>();
			}
		}
		return korisnici;

	}

	public static void save() {
		try {
			FileOutputStream fos = new FileOutputStream("korisnici.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(korisnici);
			oos.close();
		} catch (Exception e) {
			System.out.println("lose se snimili korisnici");
		}
	}
	
	
	
}
