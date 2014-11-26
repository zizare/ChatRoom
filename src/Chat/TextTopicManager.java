package Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextTopicManager implements ITopicManager {
	protected Map<String,IChatRoom> mapTopic = new HashMap<String, IChatRoom>();


	public void CreerTopic(String topic) {
		
		mapTopic.put(topic,new TextChatRoom(topic));
	}

	public IChatRoom RejoindreTopic(String topic) {
		
		return mapTopic.get(topic);
		
	}

	
	public List<String> listerTopics() {
//		return new ArrayList<String>(mapTopic.keySet());
		
		
		List<String> tmp=new ArrayList<String>();

		for(String elt : mapTopic.keySet()  )
		{
			
				tmp.add(elt);
		}
		return tmp;
	}

}
