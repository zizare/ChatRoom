package Authentification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.TreeSet;

public class Authentification implements GestAuthentification {

	TreeSet<User> Users=new TreeSet<User>();


	@Override
	public void addUser(String login, String pass) throws UserExists {
		// TODO Auto-generated method stub

		User cpr=Users.ceiling(new User(login));
		if(cpr!=null)
		{
			if(cpr.compareTo(new User(login))==0)
			{
				throw new UserExists(login);
			}
			else
			{
				Users.add(new User(login,pass));
			}
		}
		else
		{
			Users.add(new User(login,pass));
		}
		


	}

	@Override
	public void authentify(String login, String Pass) throws UserUnknown, WrongPassword {
		// TODO Auto-generated method stub
		User tmp= Users.ceiling(new User(login));
		if(tmp!=null)
		{
			if(tmp.compareTo(new User(login))==0)
			{
				if(tmp.Goodpass(Pass)==0)
				{
					//VOUS etes connecté !! ouhou !
				}
				else
				{
					//FAUX
					throw new WrongPassword(login);
				}
			}
			else
			{
				throw new UserUnknown(login);
			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public void load(String path) throws IOException {
		// TODO Auto-generated method stub
		try
		{
			FileInputStream fichier = new FileInputStream(path);
			ObjectInputStream o = new ObjectInputStream(fichier);
			Users=(TreeSet<User>)o.readObject();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeUser(String login) throws UserUnknown {
		// TODO Auto-generated method stub
		User tmp= Users.ceiling(new User(login));
		if(tmp!=null)
		{
	
			if(Users.remove(tmp))
			{
				
			}
			else
			{
				throw new UserUnknown(login);
			}
		}
		else
		{
			throw new UserUnknown(login);
		}
	}

	@Override
	public void save(String path) throws IOException {
		// TODO Auto-generated method stub
		FileOutputStream fichier = new FileOutputStream(path);
		ObjectOutputStream oos = new ObjectOutputStream(fichier);
		oos.writeObject(Users);
		oos.flush();
		oos.close();

	}


}
