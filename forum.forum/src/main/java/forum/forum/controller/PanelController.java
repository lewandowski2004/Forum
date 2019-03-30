package forum.forum.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.forum.dao.CategoryDao;
import forum.forum.dao.MessageDao;
import forum.forum.dao.RoleDao;
import forum.forum.dao.SubcategoryDao;
import forum.forum.dao.TopicDao;
import forum.forum.dao.UserDao;
import forum.forum.dto.Topic;
import forum.forum.dto.User;
import forum.forum.dto.Category;
import forum.forum.dto.Entry;
import forum.forum.dto.Message;
import forum.forum.dto.Role;
import forum.forum.dto.Subcategory;

@Controller
@RequestMapping("/panel")
public class PanelController {
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private SubcategoryDao subcategoryDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private MessageDao messageDao;
	
	private static final Logger logger = LoggerFactory.getLogger(PanelController.class);
	
	
	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.GET)
	public ModelAndView deleteUser(
			@RequestParam(name="success", required=false)String success,
			@RequestParam(name = "id", required = false) String stringId) {
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", userDao.getAllUsers());
		
		if(success != null) {
			if(success.equals("delete")) {
				mv.addObject("message", "Usuwanie zakończone powodzeniem !");
			}
		}	
		return mv;
	}
	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.POST)
	public String deleteUser(@ModelAttribute("user") User user,
			@RequestParam(name = "id", required = false) String stringId) {

		int id = Integer.parseInt(stringId);
		userDao.delete(id);
		return "redirect:/panel/deleteUser?success=delete";		
	}
	
	@RequestMapping(value = { "/getUser" }, method = RequestMethod.GET)
	public ModelAndView getUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user, @RequestParam(name = "login", required = false) String login,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@RequestParam(name = "id", required = false) String stringId) {
		
		ModelAndView mv = new ModelAndView("user");
		ModelAndView mvc = new ModelAndView("users");
		mvc.addObject("users", userDao.getAllUsers());
		
		if (stringId != null) {
			int id = Integer.parseInt(stringId);
			User u = userDao.getUser(id);
			mv.addObject("user", u);
		}
		return mv;
	}
	
	@RequestMapping(value= {"/getUser"}, method = RequestMethod.POST)
	public String getUser(HttpServletRequest request,
			@Valid @ModelAttribute("user") User user,BindingResult results,Model model, 
			@RequestParam(name = "login", required = false) String login,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "rol", required = false) String role,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname,
			@RequestParam(name = "id", required = false) String stringId,
			@RequestParam String action) {
		
		if(results.hasErrors()) {
			return "redirect:/panel/user?id="+ stringId;
		}
		int id = Integer.parseInt(stringId);
		switch (action.toLowerCase()) {
			case "update":
				if (userDao.getUserLogin(login) != null) {
					model.addAttribute("blad", "taki login juz istnieje !!!");
				} else
				if (userDao.edit(user))
					return "redirect:/panel/users?success=update";
					break;	
			case "delete":
				if(userDao.delete(id))
				return "redirect:/panel/users?success=delete";
				break;
		}
		return "redirect:/panel/user?id="+ stringId;
			
	}
	
	@RequestMapping(value = { "/getMessage" }, method = RequestMethod.GET)
	public ModelAndView getMessage(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("viewMessage") Message message,
	@RequestParam(name = "id", required = false) String id){
		
		ModelAndView mv = new ModelAndView("viewMessage");
		
		
		if (id != null) {
			int idMessage = Integer.parseInt(id);
			Message m = messageDao.getMessage(idMessage);
			mv.addObject("message", m);
		}
		

		return mv;
	}
	@RequestMapping(value= {"/getMessage"}, method = RequestMethod.POST)
	public ModelAndView getMessagee(HttpServletRequest request,
			@ModelAttribute("viewMessage") Message message,
			@RequestParam(name = "id", required = false) String id) {
		
		ModelAndView mv = new ModelAndView("viewMessage");
				return mv;
		}
	
	@RequestMapping(value = "/addCategory", method = RequestMethod.GET)
	public ModelAndView addCategoryForm(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="success", required=false)String success) {
		ModelAndView mv = new ModelAndView("addCategory");

		mv.addObject("category", new Category());
		if(success != null) {
			if(success.equals("category")) {
				mv.addObject("message", "Kategoria dodana !");
			}
		}
		return mv;
	}
	@RequestMapping(value= {"/addCategory"}, method = RequestMethod.POST)
	public String addCategoryAction(
			@Valid @ModelAttribute("category") Category category,BindingResult results,Model model,
			@RequestParam(name = "name", required = false) String name) {
		
		if(results.hasErrors()) {
			return "addCategory";
		}
		logger.info(category.toString());
		
			if (categoryDao.getNameCategory(name) != null) {
				model.addAttribute("blad", "taka nazwa juz istnieje !!!");
			} else {
				categoryDao.addCategory(category);
				return "redirect:/panel/addCategory?success=category";
			}
		return "addCategory";
	}
	@RequestMapping(value = "/addSubcategory", method = RequestMethod.GET)
	public ModelAndView addSubcategoryForm(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="success", required=false)String success) {
		ModelAndView mv = new ModelAndView("addSubcategory");

		mv.addObject("subcategory", new Subcategory());
		if(success != null) {
			if(success.equals("subcategory")) {
				mv.addObject("message", "podkategoria dodana !");
			}
		}
		return mv;
	}

	@RequestMapping(value= {"/addSubcategory"}, method = RequestMethod.POST)
	public String addSubcategoryAction(
			@Valid @ModelAttribute("subcategory") Subcategory subcategory,BindingResult results,Model model,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "cat", required = false) String category){
		
		if(results.hasErrors()) {
			return "addSubcategory";
		}
		logger.info(subcategory.toString());
		
			if (subcategoryDao.getNameSubcategory(name) != null) {
				model.addAttribute("blad", "taka nazwa juz istnieje !!!");
			} else {

				 int id = Integer.parseInt(category); 
				 Category c = categoryDao.getCategory(id);
				 subcategory.setCategory(c);
				 subcategory.setName(name);
				subcategoryDao.addSubcategory(subcategory);
				return "redirect:/panel/addSubcategory?success=subcategory";
			}
		return "addSubcategory";
	}
	
	
	@RequestMapping(value = { "/deleteTopic" }, method = RequestMethod.GET)
	public ModelAndView deleteTopic(HttpServletRequest request,
			@RequestParam(name="success", required=false)String success) {
		ModelAndView mv = new ModelAndView("topics");

		mv.addObject("topics", topicDao.getAllTopics());
		
		if(success != null) {
			if(success.equals("delete")) {
				mv.addObject("message", "Usuwanie zakończone powodzeniem !");
			}
		}
		return mv;
	}
	@RequestMapping(value = { "/deleteTopic" }, method = RequestMethod.POST)
	public String deleteTopic(
			@ModelAttribute("topic") Topic topic,
			@RequestParam(name = "id", required = false) String stringId) {

		int id = Integer.parseInt(stringId);
		topicDao.delete(id);
		return "redirect:/panel/topic?success=delete";
	}
	
	
	@RequestMapping(value = { "/deleteMessage" }, method = RequestMethod.GET)
	public ModelAndView deleteMessage(HttpServletRequest request,
			@RequestParam(name="success", required=false)String success) {
		ModelAndView mv = new ModelAndView("messages");

		mv.addObject("messages", messageDao.getAllMessages());
		
		if(success != null) {
			if(success.equals("delete")) {
				mv.addObject("message", "Usuwanie zakończone powodzeniem !");
			}
		}
		return mv;
	}
	@RequestMapping(value = { "/deleteMessage" }, method = RequestMethod.POST)
	public String deleteMessage(
			@ModelAttribute("messages") Message message,
			@RequestParam(name = "id", required = false) String stringId) {

		int id = Integer.parseInt(stringId);
		messageDao.delete(id);
		return "redirect:/panel/deleteMessage?success=delete";
	}
	
	@ModelAttribute("categories")
	public List<Category> getSubkategoria(){
		return categoryDao.getAllCategories();
	}
}
