package forum.forum.dto;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="subcategory")
public class Subcategory {
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "Wprowadz dane !")
	private String name;
	@OneToMany(mappedBy="subcategory", fetch=FetchType.EAGER)
	@OrderBy("date ASC")
	private Set<Topic> topics;
	
	@ManyToOne
	@JoinColumn(name="cat_id")
	private Category category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Topic> getTopics() {
		return topics;
	}

	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", topic=" + topics + "]";
	}
	
	
	
}
