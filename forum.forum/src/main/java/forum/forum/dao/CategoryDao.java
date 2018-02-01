package forum.forum.dao;

import java.util.List;

import forum.forum.dto.Category;


public interface CategoryDao {
	
	public List<Category> getAllCategories();
	public boolean addCategory(Category c);
	public Category getCategory(int id);
	Category getNameCategory(String name);
	
}
