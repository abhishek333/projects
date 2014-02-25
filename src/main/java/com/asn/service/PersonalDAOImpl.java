package com.asn.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.PersonalDAO;
import com.asn.model.PersonalDetails;

@Repository
public class PersonalDAOImpl implements PersonalDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){		
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Long savePersonalInfo(PersonalDetails personalDetails) {
		return (Long)getCurrentSession().save(personalDetails);
	}

	@Override
	public void updatePersonalInfo(PersonalDetails personalDetails) {
		getCurrentSession().update(personalDetails);		
	}

	@Override
	public PersonalDetails getPersonalInfo(Long id) {	
		return (PersonalDetails)getCurrentSession().get(PersonalDetails.class, id);
	}

	@Override
	public PersonalDetails getPersonalInfo(String email) {		
		return (PersonalDetails) getCurrentSession().createQuery("FROM PersonalDetails where email='"+email+"'").uniqueResult();
	}

	@Override
	public void deletePersonalInfo(PersonalDetails personalDetails) {
		getCurrentSession().delete(personalDetails);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PersonalDetails> getPersonalInfoByQuery(String queryHQL) {		
		return getCurrentSession().createQuery(queryHQL).list();
	}
}
