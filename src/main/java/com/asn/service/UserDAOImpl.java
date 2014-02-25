package com.asn.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.UserDAO;
import com.asn.model.User;

@Repository
public class UserDAOImpl implements UserDAO {	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){		
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Long createUser(User user) {				
		return (Long) getCurrentSession().save(user);		
	}

	@Override
	public void updateUser(User user) {
		getCurrentSession().update(user);		
	}

	@Override
	public User findByEmail(String email) {
		User user = (User) getCurrentSession().createQuery("FROM User where username='"+email+"'").uniqueResult();		
		return user;
	}

	@Override
	public void updateUserPassword(Long id, String password) {
		getCurrentSession().createSQLQuery("UPDATE users SET password = "+password+" WHERE id = "+id.intValue());
	}

}
