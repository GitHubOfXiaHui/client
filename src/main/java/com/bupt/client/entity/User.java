package com.bupt.client.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User extends IdLongEntity {

	private static final long serialVersionUID = 8404413962188967300L;

	@Column
	private String username;

	@Column
	private String password;

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

	@Override
	public String toString() {
		return "User{username = '" + username + "', password = '" + password + "'}";
	}

}
