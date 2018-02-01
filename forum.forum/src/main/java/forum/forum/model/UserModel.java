package forum.forum.model;

import java.io.Serializable;
import java.util.List;

import forum.forum.dto.Role;


public class UserModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String login;
	private List<Role> roles;
	
	@Override
	public String toString() {
		return "UserModel [id=" + id + ", login=" + login + ", role=" + roles + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	

}
