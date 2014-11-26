package ConnectionTcP;

import java.io.IOException;

public interface MessageConnection {
	public Message getMessage() throws IOException;
	public void sendMessage(Message m) throws IOException;
	

}
