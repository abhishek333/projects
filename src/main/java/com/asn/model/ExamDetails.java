package com.asn.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name="examdetails")
public class ExamDetails {
	
	@Id
	@GeneratedValue
	private Long id;
	@Range(min=1, message="select student")
	@Column(nullable=false)
	private Long studentId;
	@Range(min=1,message="select exam")
	@Column(nullable=false)
	private Long examId;
	@Range(min=50,message="Enter obtain mark!")
	@Column(nullable=false)
	private Float obtainMark;
	@Transient
	private Exam exam;
	@Transient
	private PersonalDetails personalDetails;
	@Transient
	private List<PersonalDetails> studentList;
	@Transient
	private Courses courses;
	
	public ExamDetails(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getObtainMark() {
		return obtainMark;
	}

	public void setObtainMark(Float obtainMark) {
		this.obtainMark = obtainMark;
	}
	
	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	
	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	
	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public List<PersonalDetails> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<PersonalDetails> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "ExamDetails [id=" + id + ", studentId=" + studentId
				+ ", examId=" + examId + ", obtainMark=" + obtainMark
				+ ", exam=" + exam + ", personalDetails=" + personalDetails
				+ "]";
	}
	
}
