package com.asn.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.RoleDAO;
import com.asn.model.Role;

@Repository
public class RoleDAOImpl implements RoleDAO {
	
	@Autowired private SessionFactory sessionFactory;
	private Session getCurrentSession(){		
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void insertRole(Role securityRole) {				
		  getCurrentSession().save(securityRole);
	}

	@Override
	public void updateRole(Role securityRole) {
		getCurrentSession().update(securityRole);
	}

	@Override
	public void deleteRole(Role securityRole) {
		getCurrentSession().delete(securityRole);
	}

	@Override
	public Role getRole(Long userId) {	
		return (Role) getCurrentSession().createQuery("From Role where user_id = "+userId).uniqueResult();
	}

	@Override
	public Integer getRoleName(Long userId) {	
		Integer role = (Integer) getCurrentSession().createSQLQuery("SELECT role FROM user_roles WHERE user_id = "+userId).uniqueResult();
		return (role==null)? 1 : role ;
	}

}
