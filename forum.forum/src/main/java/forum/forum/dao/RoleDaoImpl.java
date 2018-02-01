package forum.forum.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import forum.forum.dto.Category;
import forum.forum.dto.Role;



@Repository("roleDao")
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	@Transactional
	public List<Role> getAllRoles() {
		String getAllRoles = "from Role";
		return sessionFactory
				.getCurrentSession()
					.createQuery(getAllRoles, Role.class)
							.getResultList();
		
	}
	
	
}
