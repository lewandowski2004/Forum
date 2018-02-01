package forum.forum.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import forum.forum.dto.Category;
import forum.forum.dto.Entry;
import forum.forum.dto.Message;
import forum.forum.dto.Role;
import forum.forum.dto.SecondaryEntry;
import forum.forum.dao.SubcategoryDao;
import forum.forum.dao.TopicDao;
import forum.forum.dao.UserDao;
import forum.forum.dao.CategoryDao;
import forum.forum.dao.EntryDao;
import forum.forum.dao.MessageDao;
import forum.forum.dao.SecondaryEntryDao;
import forum.forum.dto.User;
import forum.forum.dto.Subcategory;
import forum.forum.dto.Topic;

@Controller
public class PageController {

	@Autowired
	private SubcategoryDao subcategoryDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private TopicDao topicDao;
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private SecondaryEntryDao secondaryEntryDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private EntryDao entryDao;

/* ------------------------------------ LOGIN -------------------------------*/

	@RequestMapping(value = { "/login" })
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		return mv;
	}
/* ------------------------------------ HOME PAGE -------------------------------*/
	
	@RequestMapping(value = { "/", "/home", "/index" }, method = RequestMethod.GET)
	public ModelAndView start(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "idd", required = false) String id) {
		ModelAndView mv = new ModelAndView("index");

		mv.addObject("topics", topicDao.getAllTopics());
		/*mv.addObject("ilosc", iTemat.pobierzIloscTematowWKategori(id));*/
		return mv;
	}

/* ------------------------------------ TOPIC IN CATEGORY -------------------------------*/
	
	@RequestMapping("/TopicInCategory")
	public ModelAndView TopicInCategory(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name = "id", required = false) String id) {
		ModelAndView mv = new ModelAndView("indexCategory");
		
		int TematId = Integer.parseInt(id);
		
		List<Topic> topics = topicDao.getTopicsInCategory(TematId);
		mv.addObject("topics", topics);
		
		return mv;
	}

/* ------------------------------------ LOGOUT -------------------------------*/	
	
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}

/* ------------------------------------ REGISTER -------------------------------*/
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView Register(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(name="success", required=false)String success) {
		ModelAndView mv = new ModelAndView("register");

		mv.addObject("user", new User());
		
		if(success != null) {
			if(success.equals("user")) {
				mv.addObject("message", "Rejestracja zakończona powodzeniem !");
			}
		}
		
		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String RegisterUser( 
			@Valid @ModelAttribute("user") User user,BindingResult results,Model model, 
			@RequestParam(name = "login", required = false) String login,
			@RequestParam(name = "password", required = false) String password,
			@RequestParam(name = "confirmPassword", required = false) String confirmPassword,
			@RequestParam(name = "email", required = false) String email,
			@RequestParam(name = "firstname", required = false) String firstname,
			@RequestParam(name = "lastname", required = false) String lastname) {

		if(results.hasErrors()) {
			return "register";
		}
		
		if (password.equals(confirmPassword) && !"".equals(password)) {
			if (userDao.getUserLogin(login) != null) {
				model.addAttribute("blad", "taki login juz istnieje !!!");
			} else {
				Role r = new Role();
				r.setId(2);
				List<Role> lista = new ArrayList<>();
				lista.add(r);
				user.setRoles(lista);
				userDao.addUser(user);
				return "redirect:/register?success=user";
			}

		} else
			model.addAttribute("blad", "Hasła są niezgodne !!!");
		return "register";
	}

/* ------------------------------------ CATEGORY  -------------------------------*/
	@ModelAttribute("subcategories")
	public List<Subcategory> getKategoria(){
		return subcategoryDao.getAllSubcategories();
	}
	
	@ModelAttribute("categories")
	public List<Category> getSubkategoria(){
		return categoryDao.getAllCategories();
	}
	
	
	
/* ------------------------------------ TOPIC -------------------------------*/
	
	@RequestMapping("/topic")
	public ModelAndView temat(Model model, HttpServletRequest request, @ModelAttribute("topic") Topic topic,
			@RequestParam(name = "id", required = false) String stringId) {
		ModelAndView mv = new ModelAndView("topic");
		mv.addObject("entry", new Entry());

		if (stringId != null) {
			int id = Integer.parseInt(stringId);
			Topic t = topicDao.getTopic(id);
			mv.addObject("topic", t);
		}

		return mv;
	}
/* ------------------------------------ NEW TOPIC -------------------------------*/
	@RequestMapping(value = "/newTopic", method = RequestMethod.GET)
	public ModelAndView NewTopic(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("newTopic");
		mav.addObject("topic", new Topic());

		return mav;
	}
	
	@RequestMapping(value = "/newTopic", method = RequestMethod.POST)
	public String NewTopic(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("topic") Topic topic, @RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "content", required = false) String content,
			@RequestParam(name = "cat", required = false) String category) {
		ModelAndView mav = new ModelAndView("newTopic");
		/* String uzytkownik = request.getParameter("uzytkownik"); */

		if (!"".equals(title) && !"".equals(content)) {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User uzytkowni = userDao.getUserLogin(authentication.getName());
		
			int id = Integer.parseInt(category); 
			Subcategory k = subcategoryDao.getSubcategory(id);
			 
			 
			 Date d = new Date();
			 topic.setCategory(k);
			 topic.setUser(uzytkowni);
			 topic.setDate(d);
			 topic.setContent(content);
			 topic.setTitle(title);
			

			if (topicDao.addTopic(topic))
				return "redirect:/topic?id=" + topic.getId();
		}
		
			return "newTopic";
	}
	
	/* ------------------------------------ MESSAGE -------------------------------*/
	@RequestMapping(value = "/message", method = RequestMethod.GET)
	public ModelAndView message(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("message", new Message());

		return mav;
	}
	
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public String message(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("message") Message message, 
			@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "content", required = false) String content) {
		ModelAndView mav = new ModelAndView("message");
		/* String uzytkownik = request.getParameter("uzytkownik"); */

		if (!"".equals(title) && !"".equals(content)) {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User uzytkowni = userDao.getUserLogin(authentication.getName());
		
			
			 Date d = new Date();
			 message.setTitle(title);
			 message.setDate(d);
			 message.setContent(content);
			 message.setUser(uzytkowni);

			

			if (messageDao.addMessage(message))
				return "redirect:/index";
		}
		
			return "message";
	}
	
	
/* ------------------------------------ ENTRY -------------------------------*/
	@RequestMapping(value = "/entry", method = RequestMethod.POST)
	public String Entry(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("entry") Entry entry, @RequestParam(name = "idd", required = false) String id,
			@RequestParam(name = "content", required = false) String content) {
		
		if(!"0".equals(id) && !"".equals(id) && !"".equals(content)) {
			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User uzytkowni = userDao.getUserLogin(authentication.getName());
		
		
			int idd = Integer.parseInt(id);
			Topic t = topicDao.getTopic(idd);
			entry = new Entry();
			entry.setDate(new Date());
			entry.setContent(content);
			entry.setUser(uzytkowni);
			entry.setTopic(t);
			
			if(entryDao.addEntry(entry))
				return "redirect:/topic?id=" + id;
			
		}
			return "redirect:/topic?id=" + id;
		
	}
	@RequestMapping(value = "/entry", method = RequestMethod.GET)
	public ModelAndView Entry(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("topic");
		mav.addObject("entry", new Entry());
		
		return mav;
	}
	
	/* ------------------------------------ ENTRY -------------------------------*/
	@RequestMapping(value = "/secondaryEntry", method = RequestMethod.POST)
	public String SecondaryEntry(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("secondaryEntry") SecondaryEntry secondaryEntry, @RequestParam(name = "idd", required = false) String id,
			 @RequestParam(name = "idTopic", required = false) String idTopic,
			@RequestParam(name = "content", required = false) String content) {
		
		if(!"0".equals(id) && !"".equals(id) && !"".equals(content)) {
			
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User uzytkowni = userDao.getUserLogin(authentication.getName());
		
		
			int idd = Integer.parseInt(id);
			Entry e = entryDao.getEntry(idd);
			secondaryEntry = new SecondaryEntry();
			secondaryEntry.setDate(new Date());
			secondaryEntry.setContent(content);
			secondaryEntry.setUser(uzytkowni);
			secondaryEntry.setEntry(e);
			
			if(secondaryEntryDao.addSecondaryEntry(secondaryEntry))
				return "redirect:/topic?id=" + idTopic;
			
		}
			return "redirect:/topic?id=" + idTopic;
		
	}
	@RequestMapping(value = "/secondaryEntry", method = RequestMethod.GET)
	public ModelAndView SecondaryEntry(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("topic");
		mav.addObject("secondaryEntry", new SecondaryEntry());
		
		return mav;
	}
	
	

}
