package Chat;

public interface IChatRoom {
	void poster(String msg, IChatter c);
	void quitter(IChatter c);
	void rejoindre(IChatter c);
	String getTopic();
	public String listerparticipant();

}
