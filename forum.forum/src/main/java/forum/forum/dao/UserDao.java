package forum.forum.dao;

import java.util.List;

import forum.forum.dto.User;

public interface UserDao {
	public User getUserLogin(String login);
	public boolean addUser(User u);
	public User getUser(int id);
	public boolean edit(User u);
	public boolean delete(int id);
	public List<User> getAllUsers();
		

}
