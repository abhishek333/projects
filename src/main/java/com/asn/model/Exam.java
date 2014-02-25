package com.asn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="exam")
public class Exam {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private Date examDate;
	@Column(nullable=false)
	private String examName;
	@Column(nullable=false)
	private Integer semeter;
	@Column(nullable=false)
	private Float fullMark;

	@Column(nullable=false)
	private Long courseId;
	
	public Exam(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public Integer getSemeter() {
		return semeter;
	}

	public void setSemeter(Integer semeter) {
		this.semeter = semeter;
	}

	public Float getFullMark() {
		return fullMark;
	}

	public void setFullMark(Float fullMark) {
		this.fullMark = fullMark;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", examDate=" + examDate + ", examName="
				+ examName + ", semeter=" + semeter + ", fullMark=" + fullMark
				+ ", courseId=" + courseId + "]";
	}
	
}
