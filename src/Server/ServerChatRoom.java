
package Server;

import java.util.List;
import java.io.IOException;
import java.util.Vector;

import Chat.IChatRoom;
import Chat.IChatter;
import Chat.TextChatRoom;
import ConnectionTcP.Header;
import ConnectionTcP.Message;
import ConnectionTcP.TCPServer;

public class ServerChatRoom extends TCPServer implements IChatter, IChatRoom{

	private String Pseudo;
	private String topic;
	TextChatRoom concretCR ;
	public ServerChatRoom(String s) {
		// TODO Auto-generated constructor stub
		topic=s;
		concretCR = new TextChatRoom(s);
		
	}

	@Override
	public void gereClient() {
		// TODO Auto-generated method stub
		
		try {
			
			Message m=getMessage();
			
			switch (m.head)	{
			case JOINCR: 
				Pseudo = m.data.firstElement();
				this.rejoindre(this);			
			break;
			case POST: this.poster(m.data.lastElement(),this);
			break;
			case QUITCR : this.quitter(this); break;
			case LISTP : 
				this.sendMessage(new Message(Header.RECV_LISTP, this.listerparticipant()));
				break;
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	@Override
	public String getPseudo() {
		// TODO Auto-generated method stub
		return Pseudo;
	}

	@Override
	public void recevoirMessage(String msg, IChatter c) {
		// TODO Auto-generated method stub
					
				List<String> L=new Vector<String>();
				L.add(msg);
				L.add(c.getPseudo());
				
				Message m =new Message(Header.RECV_MSG, L);
				
				System.out.println(" recev"+m.toString());
						
				try {
					this.sendMessage(m);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		
	}

	public String getTopic() {
		return concretCR.getTopic();
	}

	public String listerparticipant() {
		return concretCR.listerparticipant();
	}

	public void poster(String msg, IChatter c) {
		concretCR.poster(msg, c);
	}

	public void quitter(IChatter c) {
		concretCR.quitter(c);
	}

	public void rejoindre(IChatter c) {
		concretCR.rejoindre(c);
	}

	

}
