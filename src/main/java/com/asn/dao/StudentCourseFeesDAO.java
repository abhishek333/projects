package com.asn.dao;

import java.util.List;

import com.asn.model.StudentCourseFees;

public interface StudentCourseFeesDAO {
	Long save(StudentCourseFees studentCourseFees);
	void update(StudentCourseFees studentCourseFees);
	void delete(StudentCourseFees studentCourseFees);
	StudentCourseFees get(Long studentId);
	List listAll();
}
