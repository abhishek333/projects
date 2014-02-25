package com.asn.dao;

import java.util.List;

import com.asn.model.Exam;
import com.asn.model.ExamDetails;

public interface ExamDetailsDAO {
	Long save(ExamDetails examDetails);
	Long addExam(Exam exam);
	Exam getExam(Long id);
	void deleteExam(Exam exam);
	List listExams();
	void update(ExamDetails examDetails);
	void delete(ExamDetails examDetails);
	List getExamDetails(Long studentId);
	ExamDetails get(Long id);	
	List list();
	List getExamDetailsByStdId(Long id);
}
