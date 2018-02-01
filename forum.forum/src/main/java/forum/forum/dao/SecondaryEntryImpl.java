package forum.forum.dao;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forum.forum.dto.SecondaryEntry;


@Repository("secondaryEntryDao")
@Transactional
public class SecondaryEntryImpl implements SecondaryEntryDao {

	

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addSecondaryEntry(SecondaryEntry e) {
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

}
