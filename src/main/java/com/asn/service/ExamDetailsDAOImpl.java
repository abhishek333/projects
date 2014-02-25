package com.asn.service;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asn.dao.ExamDetailsDAO;
import com.asn.model.Exam;
import com.asn.model.ExamDetails;
@Repository
public class ExamDetailsDAOImpl implements ExamDetailsDAO {
	@Autowired private SessionFactory sessionFactory;
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public Long save(ExamDetails examDetails) {		
		return (Long) getCurrentSession().save(examDetails);
	}

	@Override
	public void update(ExamDetails examDetails) {
		getCurrentSession().update(examDetails);
	}

	@Override
	public void delete(ExamDetails examDetails) {
		// TODO Auto-generated method stub

	}

	@Override
	public List getExamDetails(Long studentId) {		
		return getCurrentSession().createCriteria(ExamDetails.class).add(Restrictions.eq("studentId", studentId)).list();
	}

	@Override
	public ExamDetails get(Long id) {
		return (ExamDetails) getCurrentSession().get(ExamDetails.class, id);
	}

	@Override
	public List list() {		
		return getCurrentSession().createCriteria(ExamDetails.class).list();
	}
	@Override
	public Long addExam(Exam exam) {		
		return (Long) getCurrentSession().save(exam);
	}
	@Override
	public void deleteExam(Exam exam) {
		getCurrentSession().delete(exam);
	}
	@Override
	public List listExams() {
		return getCurrentSession().createCriteria(Exam.class).list();
	}
	@Override
	public Exam getExam(Long id) {
		return (Exam) getCurrentSession().get(Exam.class, id);
	}
	@Override
	public List getExamDetailsByStdId(Long id) {		
		return getCurrentSession().createCriteria(ExamDetails.class).add(Restrictions.eq("studentId", id)).list();
	}

}
