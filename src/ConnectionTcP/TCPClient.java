package ConnectionTcP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient implements MessageConnection {
	private  Socket sock;
	protected InetAddress adr;
	ObjectOutputStream out;
	ObjectInputStream in;
	protected int port;
	public void setServer( InetAddress adr,  int port){
		this.adr=adr;
		this.port=port;
	}
	public void connect() throws IOException{
		sock= new Socket(adr,port);
		out =new ObjectOutputStream( sock.getOutputStream());
		in= new ObjectInputStream(sock.getInputStream());
		System.out.println(sock.toString());
		
	}
	@Override
	public Message getMessage() throws IOException {
		// TODO Auto-generated method stub
		
		try {
			return (Message) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
			
			return null;
		}
		
	
	}
	@Override
	public void sendMessage(Message m) throws IOException {
		// TODO Auto-generated method stub

		
		out.writeObject(m);
			
	}
	public void disconnect() {
		// TODO Auto-generated method stub
		try {
			sock.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
