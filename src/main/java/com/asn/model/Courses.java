package com.asn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Courses {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private String courseName;
	private Integer noOfSemester;
	private Integer totalMark;
	private Integer courseTime;
	
	public Courses(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Integer getNoOfSemester() {
		return noOfSemester;
	}

	public void setNoOfSemester(Integer noOfSemester) {
		this.noOfSemester = noOfSemester;
	}

	public Integer getTotalMark() {
		return totalMark;
	}

	public void setTotalMark(Integer totalMark) {
		this.totalMark = totalMark;
	}

	public Integer getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(Integer courseTime) {
		this.courseTime = courseTime;
	}

	@Override
	public String toString() {
		return "Courses [id=" + id + ", courseName=" + courseName
				+ ", noOfSemester=" + noOfSemester + ", totalMark=" + totalMark
				+ ", courseTime=" + courseTime + "]";
	}
	
}
