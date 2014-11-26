package Authentification;

import java.io.IOException;

public interface GestAuthentification {
	void addUser(String login, String pass) throws UserExists;
	void removeUser(String login) throws UserUnknown;
	void authentify (String login, String Pass) throws UserUnknown,WrongPassword;
	void load (String path) throws IOException;
	void save (String path) throws IOException;
}

