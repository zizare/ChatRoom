package Server;

import java.io.IOException;

import Chat.TextTopicManager;

public class TCPGestTopics extends TextTopicManager{

	private static int nextPort=1665; 
	
	public void CreerTopic(String topic){
		try {
			ServerChatRoom cr = new ServerChatRoom(topic); 
			cr.startServer(nextPort++);
			mapTopic.put(topic,cr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
