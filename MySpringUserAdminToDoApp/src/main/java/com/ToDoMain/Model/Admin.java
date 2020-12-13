package com.ToDoMain.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	private String username;
	@Column
	private String password;
	@Column
	private String confirmPassword;
	@Column
	private String email;
	public Admin() {}
	public Admin(String username, String password, String confirmPassword, String email) {
		super();
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Admin [username=" + username + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", email=" + email + "]";
	}
	
}
