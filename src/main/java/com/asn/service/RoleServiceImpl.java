package com.asn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.RoleDAO;
import com.asn.dao.RoleService;
import com.asn.model.Role;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired RoleDAO roleDAO;
	
	@Override
	public void insertRole(Role securityRole) {
		roleDAO.insertRole(securityRole);
	}

	@Override
	public void updateRole(Role securityRole) {
		roleDAO.updateRole(securityRole);
	}

	@Override
	public void deleteRole(Role securityRole) {
		roleDAO.deleteRole(securityRole);
	}

	@Override
	public Role getRole(Long userId) {		
		return roleDAO.getRole(userId);
	}

	@Override
	public Integer getRoleName(Long userId) {		
		return roleDAO.getRoleName(userId);
	}
	
}
