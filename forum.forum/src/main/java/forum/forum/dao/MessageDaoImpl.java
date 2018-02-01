package forum.forum.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forum.forum.dto.Message;
import forum.forum.dto.Topic;

@Repository("messageDao")
@Transactional
public class MessageDaoImpl implements MessageDao {
	@Autowired
	private SessionFactory sessionFactory;

	public MessageDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Message> getAllMessages() {
		String getAllMessages = "from Message m ORDER BY m.date DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(getAllMessages, Message.class)
						.getResultList();
	}

	@Override
	public boolean addMessage(Message m) {
		try {
			sessionFactory
				.getCurrentSession()
					.persist(m);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Message getMessage(int id) {
		try {
			return sessionFactory
					.getCurrentSession()
						.get(Message.class, Integer.valueOf(id));
			} catch (Exception ex) {
				ex.printStackTrace();
		}
		return null;
	}

	
	@Override
	public void delete(int id) {
		sessionFactory
			.getCurrentSession()
				.delete(getMessage(id));
		
	}


	
}
