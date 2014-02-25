package com.asn.dao;

import java.util.List;

import com.asn.model.Courses;

public interface CourseDAO {
	Long saveCourse(Courses courses);
	void updateCourse(Courses courses);
	void deleteCourse(Courses courses);
	Courses getCourses(Long id);
	Courses getCourses(String name);
	List listCourses();
}
