package ConnectionTcP;

import java.io.Serializable;
import java.util.List;
import java.util.Vector;



public class Message implements Serializable{
	
	/**
	 * 
	 */
	public Message(Header h, String s) {
		// TODO Auto-generated constructor stub
		head = h;
		data = new Vector<String>();
		data.add(s);
	}
	//creer un constructeur ac une liste !
	public Message(Header h, List<String> s) {
		// TODO Auto-generated constructor stub
		head = h;
		if(s!=null){
		data = new Vector<String>(s);
		}
		else
		{
			data=null;
		}

	}
	private static final long serialVersionUID = 1L;
	public Header head;
	public Vector<String> data;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return head+data.toString();
	}
	
	
}
