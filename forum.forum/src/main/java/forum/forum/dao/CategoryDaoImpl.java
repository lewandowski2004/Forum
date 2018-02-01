package forum.forum.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forum.forum.dto.Category;



@Repository("categoryDaoImpl")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public CategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	@Transactional
	public List<Category> getAllCategories() {
		String getAllCategories = "from Category";
		return sessionFactory
				.getCurrentSession()
					.createQuery(getAllCategories, Category.class)
							.getResultList();
		
	}
	@Override
	 @Transactional
	public Category getCategory(int id){
		try {
			return sessionFactory
					.getCurrentSession()
						.get(Category.class, Integer.valueOf(id));
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
		

}

	@Override
	public boolean addCategory(Category c) {
		try {
			sessionFactory
				.getCurrentSession()
					.persist(c);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}
	@Override
	public Category getNameCategory(String name) {
		String getNameCategory = "FROM Category WHERE name = :name";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(getNameCategory, Category.class)
						.setParameter("name", name)
							.getSingleResult();		
			}catch(Exception ex) {
					return null;
	}
	}
}
