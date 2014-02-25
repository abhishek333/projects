package com.asn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="users")
public class User implements Serializable {
	
	@Id
	@GeneratedValue	
	private Long id;
	
	@Column(name="username", nullable=false, length=100, unique=true)
	private String username;
	
	@Column(name="password", nullable=false, length=200)
	private String password;
	
	@Column(name="active", nullable=false)
	private Boolean active;
		
	private Long role;
		
	private Long personalDetails;
	
	public User(){}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Long getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(Long personalDetails) {
		this.personalDetails = personalDetails;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", active=" + active + ", role=" + role
				+ ", personalDetails=" + personalDetails + "]";
	}
	
}
