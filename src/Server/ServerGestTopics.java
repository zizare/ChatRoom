package Server;


import java.io.IOException;

import ConnectionTcP.Header;
import ConnectionTcP.Message;
import ConnectionTcP.TCPServer;

public class ServerGestTopics extends TCPServer{

	TCPGestTopics concretGT=new TCPGestTopics();
	public static void main(String[] args) {
		try {
			new ServerGestTopics().startServer(1664);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void gereClient() {
		// TODO Auto-generated method stub
		
		
		try {
			
			Message m=getMessage();
			
			switch (m.head)	{
			case LISTE_TOPICS: sendMessage(new Message(Header.LISTE_TOPICS_REPLY,concretGT.listerTopics())); break;
			case CREATE_TOPIC: concretGT.CreerTopic(m.data.firstElement());
			System.out.println("creation du topic"+m.data.firstElement()); break;
			case JOIN_TOPIC: int port = ((ServerChatRoom) concretGT.RejoindreTopic(m.data.firstElement())).getPort();
				sendMessage(new Message(Header.JOIN_REPLY,Integer.toString(port)));
				break;
			
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
