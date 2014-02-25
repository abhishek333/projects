package com.asn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.type.TimestampType;

@Entity
@Table(name="attendence")
public class Attendence {
	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@Column(nullable=false)
	private Long studentId;
	private Boolean isPresent;
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type="timestamp")
	@Column(nullable=false)
	private Date attnDate;
	
	@Transient
	private String result;
	
	public Attendence() {		
	}

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

	public Boolean getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(Boolean isPresent) {
		this.isPresent = isPresent;
	}

	public Date getAttnDate() {
		return attnDate;
	}

	public void setAttnDate(Date attnDate) {
		this.attnDate = attnDate;
	}
	
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "Attendence [id=" + id + ", studentId=" + studentId
				+ ", isPresent=" + isPresent + ", attnDate=" + attnDate
				+ ", result=" + result + "]";
	}

}
