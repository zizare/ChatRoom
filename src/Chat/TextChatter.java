package Chat;

public class TextChatter implements IChatter{

	String pseudo; 
	
	public TextChatter	(String Pseudo)
	{
		pseudo=Pseudo;
	}
	
	public String getPseudo(){
		// TODO Auto-generated method stub
		return pseudo;
	}

	
	public void recevoirMessage(String msg, IChatter c) {
		System.out.println("Msg de "+ c.getPseudo()+" : "+ msg );		
	}

}
