package com.asn.dao;

import java.util.List;

import com.asn.model.CourseFeesStructure;

public interface CourseFeesService {
	Long saveCourseFess(CourseFeesStructure courseFeesStructure);
	void updateCourseFees(CourseFeesStructure courseFeesStructure);
	void deleteCourseFees(CourseFeesStructure courseFeesStructure);
	CourseFeesStructure getCourseFees(Long id);
	List listAllCourseFees();
	CourseFeesStructure getCourseFeesByCourseId(Long courseId);
}
