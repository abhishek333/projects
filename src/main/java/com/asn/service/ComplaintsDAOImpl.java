package com.asn.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.ComplaintsDAO;
import com.asn.model.Complaints;
@Repository
public class ComplaintsDAOImpl implements ComplaintsDAO {
	@Autowired private SessionFactory sessionFactory;
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Long save(Complaints complaints) {		
		return (Long) getCurrentSession().save(complaints);
	}

	@Override
	public void delete(Complaints complaints) {
		getCurrentSession().delete(complaints);
	}

	@Override
	public Complaints get(Long id) {
		return (Complaints) getCurrentSession().get(Complaints.class, id);
	}

	@Override
	public List list() {
		return getCurrentSession().createCriteria(Complaints.class).list();
	}
	@Override
	public List getByStudentId(Long studId) {		
		return getCurrentSession().createCriteria(Complaints.class).add(Restrictions.eq("studentId", studId)).list();
	}

}
