import java.net.InetAddress;
import java.net.UnknownHostException;

import Chat.*;
import Client.ClientGestTopics;

public class Test {

	public static void main(String[] args) {
		IChatter bob = new TextChatter ("Bob");
		IChatter joe = new TextChatter ("Joe");
		IChatter Gui = new TextChatter ("Gui");
		ITopicManager gt;
		
		try {
			gt = new ClientGestTopics(InetAddress.getLocalHost(),1664);
			gt.CreerTopic("java");
			
			gt.CreerTopic("test");
			System.out.println( "Les topics ouverts sont:" + gt.listerTopics());
			gt.CreerTopic("jeux");
			System.out.println("Les topics ouverts sont:"+ gt.listerTopics());
			IChatRoom cr1 = gt.RejoindreTopic("jeux");
			IChatRoom cr2 = gt.RejoindreTopic("jeux");
			
			cr1.rejoindre(bob);
			cr2.rejoindre(joe);
			
			cr1.poster("Je suis seul ou quoi ?",bob);
	
			cr1.poster("Que du vieux mec, tu vas bien ?",bob);


	
			cr2.poster("ta gueule !", joe);
			System.out.println(cr1.listerparticipant());
			cr2.quitter(joe);
			System.out.println(cr2.listerparticipant());
			cr1.quitter(bob);
		
			System.out.println(cr1.listerparticipant());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}

