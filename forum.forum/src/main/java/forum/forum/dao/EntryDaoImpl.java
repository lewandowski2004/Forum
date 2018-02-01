package forum.forum.dao;



import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forum.forum.dto.Entry;
import forum.forum.dto.Topic;

@Repository("entryDao")
@Transactional
public class EntryDaoImpl implements EntryDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addEntry(Entry e) {
		try {
			sessionFactory
				.getCurrentSession()
					.persist(e);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Entry getEntry(int id) {
		try {
			return sessionFactory
					.getCurrentSession()
						.get(Entry.class, Integer.valueOf(id));
			} catch (Exception ex) {
				ex.printStackTrace();
		}
		return null;
	}

}
