import java.io.IOException;

import Authentification.*;

public class testTP2 {
	public static void main(String[] args) {
		GestAuthentification ga = new AuthentificationOracle ("regnierg","regnierg") ;
		// test gestion utilisateurs
		try {
			ga.addUser("bob","123");
			System.out.println("Bob est ajouté !");
			ga.removeUser("bob");
			System.out.println("Bob est retiré !");
			ga.removeUser("bob");
			System.out.println("Bob est retiré deux fois !");
		} catch (UserUnknown e) {
			System.out.println(e.login +" est inconnu du système! on ne	peut le retirer.");
		} catch (UserExists e) {
			System.out.println(e.login +" est déjà dans le système! on ne peut le recréer.");
		}
		// test authentification
		try {
			ga.addUser("bob","123");
			System.out.println("Bob est ajouté !");
			ga.authentify("bob","123");
			System.out.println("Bob est authentifié !");
			ga.authentify("bob","456");
			System.out.println("Bob est authentifié avec un autre password	!");
		} catch (WrongPassword e) {
			System.out.println(e.login+" s'est trompé de password !");
		} catch (UserExists e) {
			System.out.println(e.login +" est déjà dans le système! on ne peut le recréer.");
		} catch (UserUnknown e) {
			System.out.println(e.login +" est inconnu du système! on ne	peut le retirer.");
		}
		// test sauvegarde
		try {

			ga.removeUser("bob");
			System.out.println("Bob est retiré !");

		} catch (UserUnknown e) {
			System.out.println(e.login +" est inconnu du système! on ne peut le retirer.");
		}
	}
}
