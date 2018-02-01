package forum.forum.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	@NotBlank(message = "Wprowadz dane !")
	private String firstname;
	@NotBlank(message = "Wprowadz dane !")
	private String lastname;
	@Email(message = "Wprowadz poprawny Email !")
	private String email;
	@Length(min=4, max=14, message = "Login musi zawierać min- 4 i max-14 znaków !")
	private String login;
	@NotBlank(message = "Wprowadz dane !")
	private String password;
	@Transient
	private String confirmPassword;
	@OneToMany(mappedBy="user", fetch= FetchType.EAGER)
	private Set<Topic> topics;
	@OneToMany(mappedBy="user", fetch= FetchType.EAGER)
	private Set<Message> messages;
	@OneToMany(mappedBy="user", fetch= FetchType.EAGER)
	private Set<Entry> entries;
	@ManyToMany
	@JoinTable(name="UsersAndRoles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private List<Role> roles;
	private boolean enabled = true;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public Set<Entry> getEntries() {
		return entries;
	}
	public void setEntries(Set<Entry> entries) {
		this.entries = entries;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
