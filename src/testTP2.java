import java.io.IOException;

import Authentification.*;

public class testTP2 {
	public static void main(String[] args) {
		GestAuthentification ga = new AuthentificationOracle ("regnierg","regnierg") ;
		// test gestion utilisateurs
		try {
			ga.addUser("bob","123");
			System.out.println("Bob est ajout� !");
			ga.removeUser("bob");
			System.out.println("Bob est retir� !");
			ga.removeUser("bob");
			System.out.println("Bob est retir� deux fois !");
		} catch (UserUnknown e) {
			System.out.println(e.login +" est inconnu du syst�me! on ne	peut le retirer.");
		} catch (UserExists e) {
			System.out.println(e.login +" est d�j� dans le syst�me! on ne peut le recr�er.");
		}
		// test authentification
		try {
			ga.addUser("bob","123");
			System.out.println("Bob est ajout� !");
			ga.authentify("bob","123");
			System.out.println("Bob est authentifi� !");
			ga.authentify("bob","456");
			System.out.println("Bob est authentifi� avec un autre password	!");
		} catch (WrongPassword e) {
			System.out.println(e.login+" s'est tromp� de password !");
		} catch (UserExists e) {
			System.out.println(e.login +" est d�j� dans le syst�me! on ne peut le recr�er.");
		} catch (UserUnknown e) {
			System.out.println(e.login +" est inconnu du syst�me! on ne	peut le retirer.");
		}
		// test sauvegarde
		try {

			ga.removeUser("bob");
			System.out.println("Bob est retir� !");

		} catch (UserUnknown e) {
			System.out.println(e.login +" est inconnu du syst�me! on ne peut le retirer.");
		}
	}
}
