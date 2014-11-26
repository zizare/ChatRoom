package Chat;

import java.util.List;


public interface ITopicManager {
	
	void CreerTopic(String topic);
	IChatRoom RejoindreTopic(String topic);
	List<String> listerTopics();
}
