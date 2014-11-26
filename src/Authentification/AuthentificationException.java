package Authentification;

public class AuthentificationException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String login;
	public AuthentificationException ( String s ) {
		login=s;
	
	}
}
