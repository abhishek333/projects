package com.asn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.CourseDAO;
import com.asn.dao.CourseService;
import com.asn.model.Courses;
@Service
@Transactional
public class CourseServiceImpl implements CourseService {
	@Autowired private CourseDAO courseDAO;
	@Override
	public Long saveCourse(Courses courses) {		
		return courseDAO.saveCourse(courses);
	}

	@Override
	public void updateCourse(Courses courses) {
		courseDAO.updateCourse(courses);
	}

	@Override
	public void deleteCourse(Courses courses) {
		courseDAO.deleteCourse(courses);
	}

	@Override
	public List listCourses() {
		return courseDAO.listCourses();
	}

	@Override
	public Courses getCourses(Long id) {	
		return courseDAO.getCourses(id);
	}

	@Override
	public Courses getCourses(String name) {
		return courseDAO.getCourses(name);
	}

}
