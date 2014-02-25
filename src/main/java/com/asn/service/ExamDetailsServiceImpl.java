package com.asn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.CourseService;
import com.asn.dao.ExamDetailsDAO;
import com.asn.dao.ExamDetailsService;
import com.asn.dao.PersonalService;
import com.asn.model.Exam;
import com.asn.model.ExamDetails;
@Service
@Transactional
public class ExamDetailsServiceImpl implements ExamDetailsService {
	@Autowired private ExamDetailsDAO examDetailsDAO;
	@Autowired private PersonalService personalService;
	@Autowired private CourseService courseService;
	@Override
	public Long save(ExamDetails examDetails) {
		return examDetailsDAO.save(examDetails);
	}

	@Override
	public void update(ExamDetails examDetails) {
		examDetailsDAO.update(examDetails);
	}

	@Override
	public void delete(ExamDetails examDetails) {
		examDetailsDAO.delete(examDetails);
	}

	@Override
	public List getExamDetails(Long studentId) {
		return examDetailsDAO.getExamDetails(studentId);
	}

	@Override
	public ExamDetails get(Long id) {
		return examDetailsDAO.get(id);
	}

	@Override
	public List list() {
		List<ExamDetails> l = examDetailsDAO.list();
		for(ExamDetails ex : l) {
			ex.setExam(getExam(ex.getExamId()));
			ex.setPersonalDetails(personalService.getPersonalInfo(ex.getStudentId()));
		}
		return l;
	}

	@Override
	public Long addExam(Exam exam) {
		return examDetailsDAO.addExam(exam);
	}

	@Override
	public void deleteExam(Exam exam) {
		examDetailsDAO.deleteExam(exam);
	}

	@Override
	public List listExams() {
		return examDetailsDAO.listExams();
	}

	@Override
	public Exam getExam(Long id) {
		return examDetailsDAO.getExam(id);
	}

	@Override
	public List getExamDetailsByStdId(Long id) {		
		List<ExamDetails> l = examDetailsDAO.getExamDetailsByStdId(id);
		for(ExamDetails e : l){
			e.setExam(getExam(e.getExamId()));
			e.setCourses(courseService.getCourses(e.getExam().getCourseId()));
		}
		return l;
	}

}
