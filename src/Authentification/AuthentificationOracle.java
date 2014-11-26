/** Fichier AuthentificationOracle.java */
package Authentification;
import java.sql.*;
import java.io.*;
public class AuthentificationOracle implements GestAuthentification {
	Connection conn;
	Statement stmt;
	public AuthentificationOracle (String DBlogin,String DBpass) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.err.println("Could not load the driver");
		}
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@oracle.efrei.fr:1521:sgbd", DBlogin,DBpass);
			
			// @machineName:port:SID,userid, password
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Could not connect to DB"+e);
		}
	}
	public void close () {
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void addUser(String login, String pass) throws UserExists {
		try {
			stmt.executeUpdate("INSERT INTO chat_users(login,pass)"+ "VALUES ('"+login +"','"+ pass + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void removeUser(String login) throws UserUnknown {
		try {
			System.out.println("DELETE from chat_users where login = '"+login+"'");
			if(stmt.executeUpdate("DELETE from chat_users where login = '"+login+"'")==0)
			{
				throw new UserUnknown(login);
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void authentify(String login, String Pass) throws UserUnknown,
	WrongPassword {
		try {
			ResultSet rset = stmt.executeQuery("SELECT s.login, s.pass " + "FROM chat_users s " +	"WHERE s.pass = '"+Pass + "' and s.login = '" +login+"'");
			if (rset.next()) {
				// print example :
				// System.out.println(rset.getString(1) + ", " +rset.getString(2));
			System.out.println("User "+rset.getString(1)+ " is authentified.");
			} // else no result !! bad user !
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new UserUnknown(login);
	}

	public void charger(String path) throws IOException {
		// nothing to do
	}
	public void sauver(String path) throws IOException {
		try {
			stmt.execute("commit;");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException("DB access failed !"+e);
		}
	}
	@Override
	public void load(String path) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void save(String path) throws IOException {
		// TODO Auto-generated method stub
		
	}
}