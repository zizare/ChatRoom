package ConnectionTcP;

import java.net.InetAddress;

public class BasicTCPClient {
	public static void main(String[] args) {
		TCPClient cli = new TCPClient();
		try {
			cli.setServer(InetAddress.getLocalHost(), 1664);
			cli.connect();
			cli.sendMessage(new Message(Header.DEBUG,"Ping from client !"));
					System.out.println(cli.getMessage());
			cli.disconnect();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}
