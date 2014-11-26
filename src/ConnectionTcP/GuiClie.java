package ConnectionTcP;

import java.net.InetAddress;
import java.util.Scanner;

public class GuiClie {

	public static void main(String[] args) {
		TCPClient cli = new TCPClient();
		try {
			cli.setServer(InetAddress.getLocalHost(), 1664);
			cli.connect();
			Scanner sc = new Scanner(System.in);
			String str = sc.nextLine();
			cli.sendMessage(new Message(Header.DEBUG,str));
			System.out.println(cli.getMessage());
			cli.disconnect();
		} catch (Exception e) {
			e.printStackTrace(); 
		}
	}
}


