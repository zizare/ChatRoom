package Authentification;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String login;
	private String pass;
	
	public User(String log,String pass)
	{
		this.login=log;
		this.pass=pass;
	}
	
	public User(String log)
	{
		this.login=log;
		this.pass= new String("BIDON");
	}
	

	@Override
	public int compareTo(User arg0) {
		// TODO Auto-generated method stub
		return this.login.compareTo(arg0.login);
	}
	
	public int Goodpass(String p)
	{
		return this.pass.compareTo(p);
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return  new String("log :" +this.login +" pass :"+this.pass);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}
	
	

}
