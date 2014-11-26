package Chat;

import java.util.ArrayList;
import java.util.List;

public class TextChatRoom implements IChatRoom  	{

	private String Topic =new String();
	private List<IChatter> participant= new ArrayList<IChatter>();
	
	IChatter Admin=new TextChatter("Admin");
	
	@Override
	public boolean equals(Object i) 
	{
		
		if(i==null)
		return true;
		
		return participant.contains(i);
		
	}
	

	public TextChatRoom(String topic) {
		Topic=topic;
	}

	public String getTopic() {
		// TODO Auto-generated method stub
		return Topic;
	}

	public void poster(String msg, IChatter c) {

		for (IChatter elt : participant)
		{
			elt.recevoirMessage(msg, c);
		}
	}


	public void quitter(IChatter c) {
		participant.remove(c);
		this.poster(" " +c.getPseudo()+" a quitter la chat room " + Topic,Admin);
	}
	
	

	public void rejoindre(IChatter c) {
		if(equals(c)==false)
		{
			participant.add(c);
			this.poster(" " +c.getPseudo()+" a rejoind la chat room " + Topic,Admin);
		}
		else
		{
			//echec vous etes con
		}
	}
	
	public String listerparticipant(){
		String tmp = new String();
		for(IChatter elt : participant)
		{
			tmp=elt.getPseudo()+" "+tmp;
		}
		return tmp;
	}

}
