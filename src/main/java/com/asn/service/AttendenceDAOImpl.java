package com.asn.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.AttendenceDAO;
import com.asn.model.Attendence;
@Repository
public class AttendenceDAOImpl implements AttendenceDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){		
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Long saveAttendence(Attendence attendence) {
		return (Long) getCurrentSession().save(attendence);
	}

	@Override
	public void updateAttendence(Attendence attendence) {
		getCurrentSession().update(attendence);
	}

	@Override
	public void deleteAttendence(Attendence attendence) {
		getCurrentSession().delete(attendence);
	}

	@Override
	public Attendence getAttendence(Long id) {		
		return (Attendence) getCurrentSession().get(Attendence.class, id);
	}

	@Override
	public List listAttendence() {		
		return getCurrentSession().createQuery("FROM "+Attendence.class.getName()).list();
	}

	@Override
	public Attendence getAttendence(Long studentId, Date attnDate) {		
		return (Attendence) getCurrentSession().createQuery("FROM "+Attendence.class.getName()+" a WHERE a.studentId = :studentId AND attnDate= :attnDate").setLong("studentId", studentId).setDate("attnDate", attnDate).uniqueResult();
	}

	@Override
	public List<Attendence> getAttendence(Long uid, Date fromDate, Date toDate) {
		Query query = getCurrentSession().createQuery("FROM "+Attendence.class.getName()+""
				+ " where studentId = :uid and attnDate between :startDt and :endDt");
		query.setParameter("uid", uid);
		query.setParameter("startDt", fromDate);
		query.setParameter("endDt", toDate);
		/*Criteria criteria = getCurrentSession().createCriteria(Attendence.class);
		criteria.add(Restrictions.between("attnDate", fromDate, toDate));
		return criteria.list();*/
		return query.list();
	}

}
