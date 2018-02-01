package forum.forum.test;



import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import forum.forum.dao.SubcategoryDao;
import forum.forum.dao.TopicDao;
import forum.forum.dao.UserDao;
import forum.forum.dao.EntryDao;
import forum.forum.dto.Topic;
import forum.forum.dto.User;
import forum.forum.dto.Entry;
import forum.forum.dto.Subcategory;

public class testClassCase  {
	
	private User u = null;
	private Topic t = null;
	private Subcategory k = null;
	private Entry w = null;
	private static UserDao iUzytkownik;
	private static TopicDao iTemat;
	private static EntryDao iWpis;
	private static SubcategoryDao iKategoria;
	private static AnnotationConfigApplicationContext context;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("config");
		context.scan("forum.forum.dao");
		context.scan("forum.forum.dto");
		context.refresh();
		
		iUzytkownik = (UserDao)context.getBean("iUzytkownik");
		iTemat = (TopicDao)context.getBean("iTemat");
		iWpis = (EntryDao)context.getBean("iWpis");
		iKategoria = (SubcategoryDao)context.getBean("iKategoria");
		
	}
	/*@Test
	public void tesAddUser() {
		u = new Uzytkownik();
		
		
		u.setHaslo("12345");
		u.setLogin("Lewy");

		
		assertEquals(true, iUzytkownik.dodajUzytkownika(u));
	}*/
	
	/*@Test
	public void tesAddTopic() {
		u = new Uzytkownik();
		Date d = new Date();
		t = new Temat();
		u = iUzytkownik.pobierzPoLoginie("Lewy");
		
		t.setKategoria(iKategoria.pobierzKategorie(3));
		t.setTresc("ssssassdsa");
		t.setTytul("sssssassdsss");
		t.setData(d);
		t.setUzytkownik(u);
		t.setWpisy(null);
		
		assertEquals(true, iTemat.dodajTemat(t));
	}*/
	
		@Test
		public void tesAddWPis() {
			u = iUzytkownik.getUser(9);
			
			Topic temat = iTemat.getTopic(27);
			
			w = new Entry();
			w.setDate(new Date());
			w.setContent("sss");
			w.setUser(u);
			w.setTopic(temat);
			
			
			assertEquals(true, iWpis.addEntry(w));
		}
	/*@Test
	public void tesAddKategory() {
		k = new Kategoria();
		
		k.setNazwa("Java");
		
		assertEquals(true, iKategoria.dodajKategorie(k));
	}*/
	
} 
