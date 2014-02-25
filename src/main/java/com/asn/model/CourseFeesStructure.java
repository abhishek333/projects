package com.asn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="feesstructure")
public class CourseFeesStructure {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=true)
	private Long courseId;
	@Column(nullable=false)
	private Double fullFees;
	@Transient
	private String courseName;
	
	public CourseFeesStructure(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Double getFullFees() {
		return fullFees;
	}

	public void setFullFees(Double fullFees) {
		this.fullFees = fullFees;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Override
	public String toString() {
		return "CourseFeesStructure [id=" + id + ", courseId=" + courseId
				+ ", fullFees=" + fullFees + "]";
	}
	
}
