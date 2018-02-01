package forum.forum.dao;



import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import forum.forum.dto.Subcategory;
import forum.forum.dto.Topic;
import forum.forum.dto.User;




@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public User getUserLogin(String login) {
		String getUserLogin = "FROM User WHERE login = :login";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(getUserLogin, User.class)
						.setParameter("login", login)
							.getSingleResult();		
			}catch(Exception ex) {
					//ex.printStackTrace();
					return null;
	}
	}

	@Override
	public boolean addUser(User u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		try {
			sessionFactory
				.getCurrentSession()
					.persist(u);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	@Override
	public User getUser(int id){
		try {
			return sessionFactory
					.getCurrentSession()
						.get(User.class, Integer.valueOf(id));
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean edit(User u) {
		try {
		sessionFactory
			.getCurrentSession()
				.update(u);
		return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean delete(int id) {
		try {
		sessionFactory
			.getCurrentSession()
				.delete(getUser(id));
		return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> getAllUsers() {
		String getUsers = "FROM User";
		return sessionFactory
				.getCurrentSession()
					.createQuery(getUsers, User.class)
						.getResultList();
		
	}
	

}