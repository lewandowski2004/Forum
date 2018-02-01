package forum.forum.service;

import forum.forum.dto.User;

public interface UzytkownikService {
	
	public User pobierzPoLoginie(String login);
	public boolean dodajUzytkownika(User u);
}
