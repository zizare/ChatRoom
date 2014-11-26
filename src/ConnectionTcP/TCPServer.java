package ConnectionTcP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class TCPServer implements Cloneable, Runnable, MessageConnection {

	private  Socket commSocket;
	private  ServerSocket waitSock;
	private int port;
	private boolean istreatclient;
	ObjectOutputStream out ;
	ObjectInputStream in;

	public void startServer(int port) throws IOException, ClassNotFoundException{
		this.port=port;
		istreatclient=false;
		waitSock=new ServerSocket(port);
		new Thread (this).start();

	}

	public void treatclient(){
		istreatclient=true;
	}
	public void stopServer() throws IOException{
		waitSock.close();
	}
	public void run(){
		while(true)
		{
			if(istreatclient)
			{

				gereClient();

			}
			else
			{

				try {
					commSocket=waitSock.accept();
					in= new ObjectInputStream(commSocket.getInputStream());
					out =new ObjectOutputStream( commSocket.getOutputStream());			
					System.out.println(commSocket.toString());
					try {
						TCPServer clone =(TCPServer) this.clone();
						this.treatclient();
						new Thread(clone).start();

					} catch (CloneNotSupportedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		}
	}
	public int getPort(){
		return port;}
	public abstract void gereClient();

	@Override
	public Message getMessage() throws IOException {
		// TODO Auto-generated method stub
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


}
