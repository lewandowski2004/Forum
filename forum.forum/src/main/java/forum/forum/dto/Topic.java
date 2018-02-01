package forum.forum.dto;


import java.util.Date;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="topic")

public class Topic {
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	@ManyToOne
	@JoinColumn(name="cat_id")
	private Subcategory subcategory;

	private String title;
	@Lob
	private String content;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	@OneToMany(mappedBy="topic", fetch=FetchType.EAGER)
	@OrderBy("date ASC")
	private Set<Entry> entries;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public Subcategory getCategory() {
		return subcategory;
	}



	public void setCategory(Subcategory category) {
		this.subcategory = category;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getContent() {
		return content;
	}



	public void setContent(String content) {
		this.content = content;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Set<Entry> getEntries() {
		return entries;
	}



	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}



	@Override
	public String toString() {
		return "Topic [id=" + id + ", date=" + date + ", category=" + subcategory + ", title=" + title + ", content="
				+ content + ", user=" + user + ", entries=" + entries + "]";
	}
	
	
	
}