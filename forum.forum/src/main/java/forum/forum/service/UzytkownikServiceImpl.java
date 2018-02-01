package forum.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import forum.forum.dao.UserDao;
import forum.forum.dto.User;

@Service
public class UzytkownikServiceImpl implements UzytkownikService {

	@Autowired
	UserDao iUzytkownik;
	
	@Override
	public User pobierzPoLoginie(String login) {
		return iUzytkownik.getUserLogin(login);
	}

	@Override
	public boolean dodajUzytkownika(User u) {
		return iUzytkownik.addUser(u);
	}

}
