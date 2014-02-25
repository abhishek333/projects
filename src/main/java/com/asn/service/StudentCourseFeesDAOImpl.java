package com.asn.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.StudentCourseFeesDAO;
import com.asn.model.StudentCourseFees;
@Repository
public class StudentCourseFeesDAOImpl implements StudentCourseFeesDAO {
	@Autowired private SessionFactory sessionFactory;
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Long save(StudentCourseFees studentCourseFees) {		
		return (Long) getCurrentSession().save(studentCourseFees);
	}

	@Override
	public void update(StudentCourseFees studentCourseFees) {
		getCurrentSession().update(studentCourseFees);

	}

	@Override
	public void delete(StudentCourseFees studentCourseFees) {
		getCurrentSession().delete(studentCourseFees);
	}

	@Override
	public StudentCourseFees get(Long studentId) {		
		return (StudentCourseFees) getCurrentSession().createCriteria(StudentCourseFees.class).add(Restrictions.eq("studentId", studentId)).uniqueResult();
	}

	@Override
	public List listAll() {		
		return  getCurrentSession().createCriteria(StudentCourseFees.class).list();
		
	}

}
