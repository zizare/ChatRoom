package ConnectionTcP;

import java.io.IOException;
 
public class BasicTCPServer extends TCPServer {
	@Override
	public void gereClient(){
		try {
			System.out.println(getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			sendMessage(new Message(Header.DEBUG,"pong from server"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server finished handling client, quitting.");
	}
	public static void main(String[] args) {
		BasicTCPServer serv = new BasicTCPServer();
		try {
			serv.startServer(1664);
			System.out.println("Press enter to quit...");
			System.in.read();
			serv.stopServer();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
