package com.asn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.CourseDAO;
import com.asn.dao.CourseService;
import com.asn.dao.PersonalService;
import com.asn.dao.StudentCourseFeesDAO;
import com.asn.dao.StudentCourseFeesService;
import com.asn.model.StudentCourseFees;
@Service
@Transactional
public class StudentCourseFeesServiceImpl implements StudentCourseFeesService {
	@Autowired private StudentCourseFeesDAO studentCourseFeesDAO;
	@Autowired private CourseService courseService;
	@Autowired private PersonalService personalService;
	@Override
	public Long save(StudentCourseFees studentCourseFees) {
		return studentCourseFeesDAO.save(studentCourseFees);
	}

	@Override
	public void update(StudentCourseFees studentCourseFees) {
		studentCourseFeesDAO.update(studentCourseFees);
	}

	@Override
	public void delete(StudentCourseFees studentCourseFees) {
		studentCourseFeesDAO.delete(studentCourseFees);
	}

	@Override
	public StudentCourseFees get(Long studentId) {
		StudentCourseFees scf = studentCourseFeesDAO.get(studentId);
		scf.setPers(personalService.getPersonalInfo(scf.getStudentId()));
		scf.setCourses(courseService.getCourses(scf.getCourseId()));
		return scf;
	}

	@Override
	public List listAll() {
		List<StudentCourseFees> l = studentCourseFeesDAO.listAll();
		for(StudentCourseFees scf: l) {
			scf.setPers(personalService.getPersonalInfo(scf.getStudentId()));
			scf.setCourses(courseService.getCourses(scf.getCourseId()));
		}
		return l;
	}

}
