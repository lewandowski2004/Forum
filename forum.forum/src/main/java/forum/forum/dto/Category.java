package forum.forum.dto;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="category")
public class Category {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Wprowadz dane !")
	private String name;
	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private Set<Subcategory> subcategories;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Subcategory> getSubcategories() {
		return subcategories;
	}
	public void setSubcategories(Set<Subcategory> subcategories) {
		this.subcategories = subcategories;
	}
	
	

}
