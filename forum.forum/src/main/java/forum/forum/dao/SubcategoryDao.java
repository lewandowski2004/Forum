package forum.forum.dao;

import java.util.List;

import forum.forum.dto.Subcategory;

public interface SubcategoryDao {
	
	public List<Subcategory> getAllSubcategories();
	public boolean addSubcategory(Subcategory c);
	public Subcategory getSubcategory(int id);
	Subcategory getNameSubcategory(String name);
	
}
