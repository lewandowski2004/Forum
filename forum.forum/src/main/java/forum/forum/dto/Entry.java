package forum.forum.dto;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="entry")
public class Entry {
	@Id
	@GeneratedValue
	private int id;
	private Date date;
	
	@Lob
	private String content;
	
	@ManyToOne
	@JoinColumn(name="user")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="topic")
	private Topic topic;
	
	@OneToMany(mappedBy="entry", fetch=FetchType.EAGER)
	@OrderBy("date ASC")
	private Set<SecondaryEntry> secondaryEntries;
	
	public Set<SecondaryEntry> getSecondaryEntries() {
		return secondaryEntries;
	}

	public void setSecondaryEntries(Set<SecondaryEntry> secondaryEntries) {
		this.secondaryEntries = secondaryEntries;
	}

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

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	@Override
	public String toString() {
		return "Entry [id=" + id + ", date=" + date + ", content=" + content + ", user=" + user + ", topic=" + topic
				+ "]";
	}
	
	
	
}
