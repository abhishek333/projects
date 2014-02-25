package com.asn.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.CourseFeesDAO;
import com.asn.model.CourseFeesStructure;
@Repository
public class CourseFeesDAOImpl implements CourseFeesDAO {
	@Autowired private SessionFactory sessionFactory;
	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Long saveCourseFess(CourseFeesStructure courseFeesStructure) {	
		return (Long) getCurrentSession().save(courseFeesStructure);
	}

	@Override
	public void updateCourseFees(CourseFeesStructure courseFeesStructure) {
		getCurrentSession().update(courseFeesStructure);
	}

	@Override
	public void deleteCourseFees(CourseFeesStructure courseFeesStructure) {
		getCurrentSession().delete(courseFeesStructure);
	}

	@Override
	public CourseFeesStructure getCourseFees(Long id) {
		return (CourseFeesStructure) getCurrentSession().get(CourseFeesStructure.class, id);
	}

	@Override
	public List listAllCourseFees() {		
		return getCurrentSession().createQuery("FROM "+CourseFeesStructure.class.getName()).list();
	}
	@Override
	public CourseFeesStructure getCourseFeesByCourseId(Long courseId) {	
		return (CourseFeesStructure) getCurrentSession().createCriteria(CourseFeesStructure.class).add(Restrictions.eq("courseId", courseId)).uniqueResult();
	}

}
