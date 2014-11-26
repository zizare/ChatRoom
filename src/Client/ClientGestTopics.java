package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

import Chat.IChatRoom;
import Chat.ITopicManager;
import ConnectionTcP.Header;
import ConnectionTcP.Message;
import ConnectionTcP.TCPClient;


public class ClientGestTopics extends TCPClient implements ITopicManager{
	
	ClientChatRoom javaCRproxy=new ClientChatRoom();

	public ClientGestTopics(InetAddress addr,int port) {
		
		setServer(addr, port);
		try {
			connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
	
	}


	@Override
	public void CreerTopic(String topic) {
		// TODO Auto-generated method stub
		try {
			this.sendMessage(new Message(Header.CREATE_TOPIC,topic));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public IChatRoom RejoindreTopic(String topic) {
		// TODO Auto-generated method stub
		try {
			this.sendMessage(new Message(Header.JOIN_TOPIC,topic));
			
			Message m =this.getMessage();
			
			this.port=Integer.parseInt(m.data.firstElement());
			
			javaCRproxy.setServer(this.adr, this.port);
			
			javaCRproxy.connect();
			
			return javaCRproxy;
			
			//add return §!!
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<String> listerTopics() {
		// TODO Auto-generated method stub
		try {
			this.sendMessage(new Message(Header.LISTE_TOPICS,(List<String>)null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Message m=this.getMessage();
			return m.data;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
