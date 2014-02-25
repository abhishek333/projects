package com.asn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="studentfees")
public class StudentCourseFees {
	
	@Id
	@GeneratedValue
	private Long id;
	@Column(unique=true)
	private Long studentId;
	private Long courseId;
	@Column(nullable=false)
	private Double fullFees;	
	private Double paidAmount;
	@Transient
	private Double balanceAmount;
	@Transient
	private Double payingAmount;
	
	@Transient
	private PersonalDetails pers;
	@Transient
	private Courses courses;
	
	public StudentCourseFees(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Double getFullFees() {
		return fullFees;
	}

	public void setFullFees(Double fullFees) {
		this.fullFees = fullFees;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;		
	}
	
	public PersonalDetails getPers() {
		return pers;
	}

	public void setPers(PersonalDetails pers) {
		this.pers = pers;
	}

	public Courses getCourses() {
		return courses;
	}

	public void setCourses(Courses courses) {
		this.courses = courses;
	}
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Double getPayingAmount() {
		return payingAmount;
	}

	public void setPayingAmount(Double payingAmount) {
		this.payingAmount = payingAmount;
	}

	@Override
	public String toString() {
		return "StudentCourseFees [id=" + id + ", studentId=" + studentId
				+ ", courseId=" + courseId + ", fullFees=" + fullFees
				+ ", paidAmount=" + paidAmount + ", pers=" + pers
				+ ", courses=" + courses + "]";
	}

}
