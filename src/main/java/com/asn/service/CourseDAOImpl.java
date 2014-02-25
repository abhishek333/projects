package com.asn.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.CourseDAO;
import com.asn.model.Courses;
@Repository
public class CourseDAOImpl implements CourseDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getCurrentSession(){		
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public Long saveCourse(Courses courses) {		
		return (Long) getCurrentSession().save(courses);
	}

	@Override
	public void updateCourse(Courses courses) {		
		getCurrentSession().update(courses);
	}

	@Override
	public void deleteCourse(Courses courses) {
		getCurrentSession().delete(courses);
	}

	@Override
	public List listCourses() {
		return getCurrentSession().createQuery("FROM "+Courses.class.getName()).list();
	}

	@Override
	public Courses getCourses(Long id) {		
		return (Courses) getCurrentSession().get(Courses.class, id);
	}

	@Override
	public Courses getCourses(String name) {		
		return (Courses) getCurrentSession().createCriteria(Courses.class).add(Restrictions.eq("courseName", name).ignoreCase()).uniqueResult();
	}

}
