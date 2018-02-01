package forum.forum.dao;

import java.util.List;

import forum.forum.dto.Topic;

public interface TopicDao {

	public List<Topic> getAllTopics();
	public boolean addTopic(Topic t);
	public Topic pobierzIloscTematowWKategori(String id);
	public Topic getTopic(int id);
	public List<Topic> getTopicsInCategory(int category);
	public void delete(int id);
	
}
