package forum.forum.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forum.forum.dto.Subcategory;
import forum.forum.dto.User;


@Repository("subcategoryDao")
@Transactional
public class SubcategoryDaoImpl implements SubcategoryDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public SubcategoryDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	@Transactional
	public List<Subcategory> getAllSubcategories() {
		String getAllCategories = "from Subcategory";
		return sessionFactory
				.getCurrentSession()
					.createQuery(getAllCategories, Subcategory.class)
							.getResultList();
		
	}
	@Override
	 @Transactional
	public Subcategory getSubcategory(int id){
		try {
			return sessionFactory
					.getCurrentSession()
						.get(Subcategory.class, Integer.valueOf(id));
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
		

}

	@Override
	public boolean addSubcategory(Subcategory c) {
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
	public Subcategory getNameSubcategory(String name) {
		String getNameCategory = "FROM Subcategory WHERE name = :name";
		try {
		return sessionFactory
				.getCurrentSession()
					.createQuery(getNameCategory, Subcategory.class)
						.setParameter("name", name)
							.getSingleResult();		
			}catch(Exception ex) {
					return null;
	}
	}
}
