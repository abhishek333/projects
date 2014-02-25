package com.asn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.CourseDAO;
import com.asn.dao.CourseFeesDAO;
import com.asn.dao.CourseFeesService;
import com.asn.model.CourseFeesStructure;
import com.asn.model.Courses;

@Service
@Transactional
public class CourseFeesServiceImpl implements CourseFeesService {
	@Autowired private CourseFeesDAO courseFeesDAO;
	@Autowired private CourseDAO courseDAO;
	@Override
	public Long saveCourseFess(CourseFeesStructure courseFeesStructure) {
		return courseFeesDAO.saveCourseFess(courseFeesStructure);
	}

	@Override
	public void updateCourseFees(CourseFeesStructure courseFeesStructure) {
		courseFeesDAO.updateCourseFees(courseFeesStructure);
	}

	@Override
	public void deleteCourseFees(CourseFeesStructure courseFeesStructure) {
		courseFeesDAO.deleteCourseFees(courseFeesStructure);
	}

	@Override
	public CourseFeesStructure getCourseFees(Long id) {
		return courseFeesDAO.getCourseFees(id);
	}

	@Override
	public List listAllCourseFees() {
		List<CourseFeesStructure> l = courseFeesDAO.listAllCourseFees();
		for(CourseFeesStructure c : l)
			c.setCourseName(courseDAO.getCourses(c.getCourseId()).getCourseName());
		return l;
	}

	@Override
	public CourseFeesStructure getCourseFeesByCourseId(Long courseId) {		
		return courseFeesDAO.getCourseFeesByCourseId(courseId);
	}

}
