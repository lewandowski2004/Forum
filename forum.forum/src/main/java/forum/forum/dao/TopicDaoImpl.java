package forum.forum.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forum.forum.dto.Topic;

@Repository("topicDao")
@Transactional
public class TopicDaoImpl implements TopicDao {
	@Autowired
	private SessionFactory sessionFactory;

	public TopicDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Topic> getAllTopics() {
		String getAllTopics = "from Topic t ORDER BY t.date DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(getAllTopics, Topic.class)
						.getResultList();
	}

	@Override
	public boolean addTopic(Topic t) {
		try {
			sessionFactory
				.getCurrentSession()
					.persist(t);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public Topic getTopic(int id) {
		try {
			return sessionFactory
					.getCurrentSession()
						.get(Topic.class, Integer.valueOf(id));
			} catch (Exception ex) {
				ex.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public List<Topic> getTopicsInCategory(int category) {
		String getTopicsInCategory = "FROM Topic WHERE cat_id = :id ORDER BY date DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(getTopicsInCategory, Topic.class)
						.setParameter("id", category)
							.getResultList();
	}
	@Override
	public void delete(int id) {
		sessionFactory
			.getCurrentSession()
				.delete(getTopic(id));
		
	}

	@Override
	@Transactional
	public Topic pobierzIloscTematowWKategori(String id) {
		String pobierzIlosc = "select count(*) Topic  where category= :id";
		return sessionFactory.getCurrentSession()
				.createQuery(pobierzIlosc, Topic.class)
					.setParameter("id", id)
						.getSingleResult();
	}

	
}
