package forum.forum.dao;

import java.util.List;

import forum.forum.dto.Message;

public interface MessageDao {

	public List<Message> getAllMessages();
	public boolean addMessage(Message m);
	public Message getMessage(int id);
	public void delete(int id);
	
}
