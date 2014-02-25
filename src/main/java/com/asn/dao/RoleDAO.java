package com.asn.dao;

import com.asn.model.Role;

public interface RoleDAO {
	void insertRole(Role securityRole);
	void updateRole(Role securityRole);
	void deleteRole(Role securityRole);
	Role getRole(Long userId);
	Integer getRoleName(Long userId);
}
