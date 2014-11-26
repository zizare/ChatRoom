package Client;

import java.util.List;
import java.util.Vector;
import java.io.IOException;

import Chat.IChatRoom;
import Chat.IChatter;
import Chat.TextChatter;
import ConnectionTcP.Header;
import ConnectionTcP.Message;
import ConnectionTcP.TCPClient;
import ConnectionTcP.TCPServer;

public class ClientChatRoom extends TCPClient implements IChatRoom, Runnable {

	private IChatter leChatter;
	private String topic;
	private boolean doRun=true;
	@Override
	public String getTopic() {
		// TODO Auto-generated method stub
		return topic;
	}

	@Override
	public String listerparticipant() {
		// TODO Auto-generated method stub
		try {
			this.sendMessage(new Message(Header.LISTP,(String)null));
			Message m=getMessage();
			return m.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public void poster(String msg, IChatter c) {
		// TODO Auto-generated method stub
		List<String> L = new Vector<String>();
		//possibilité inversion pseudo msg
		System.out.println(msg);
		
		L.add(c.getPseudo());
		L.add(msg);
		
		try {
			this.sendMessage(new Message(Header.POST, L ));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void quitter(IChatter c) {
		// TODO Auto-generated method stub
		try {
			this.sendMessage(new Message(Header.QUITCR,(String)null));
			this.doRun=false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void rejoindre(IChatter c) {
		// TODO Auto-generated method stub
		try {
			leChatter=c;
			this.sendMessage(new Message(Header.JOINCR,c.getPseudo()));
			new Thread(this).start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(doRun)
		{
			try {
				Message m=getMessage();
				System.out.println(m.toString());
				if(m.head==Header.RECV_MSG)
				leChatter.recevoirMessage(m.data.firstElement(),new TextChatter(m.data.lastElement()));
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



}
