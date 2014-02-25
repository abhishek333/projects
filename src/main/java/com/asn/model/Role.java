package com.asn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="user_roles")
public class Role implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="role", nullable=false)
	private Integer roleName;

	@Column(name="user_id", nullable=false, unique=true)
	private Long userId;
	
	
	public Role(){}

	public Role(Integer roleName, Long userId) {
		this.roleName = roleName;		
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleName() {
		return roleName;
	}

	public void setRoleName(Integer roleName) {
		this.roleName = roleName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "SecurityRole [roleName=" + roleName + "]";
	}
	
}	