package forum.forum.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import forum.forum.dao.UserDao;
import forum.forum.dto.User;
import forum.forum.model.UserModel;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session; 
	@Autowired
	private UserDao userDao;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		
		if(session.getAttribute("userModel")==null) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = userDao.getUserLogin(authentication.getName());
			
			if(user!=null) {
				userModel = new UserModel();
				
				userModel.setId(user.getId());
				userModel.setLogin(user.getLogin());
				userModel.setRoles(user.getRoles());
				
				return userModel;
			}
			
		}
		return (UserModel) session.getAttribute("userModel");
	}
}
