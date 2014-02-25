package com.asn.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="complaints")
public class Complaints {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String complaintQuery;
	private Date complaintDate;
	private Long studentId;
	private String complaintBy;
	@Transient private String complainTo;

	public Complaints(){}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComplaintQuery() {
		return complaintQuery;
	}

	public void setComplaintQuery(String complaintQuery) {
		this.complaintQuery = complaintQuery;
	}

	public Date getComplaintDate() {
		return complaintDate;
	}

	public void setComplaintDate(Date complaintDate) {
		this.complaintDate = complaintDate;
	}
	public String getComplainTo() {
		return complainTo;
	}

	public void setComplainTo(String complainTo) {
		this.complainTo = complainTo;
	}
	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getComplaintBy() {
		return complaintBy;
	}

	public void setComplaintBy(String complaintBy) {
		this.complaintBy = complaintBy;
	}

	@Override
	public String toString() {
		return "Complaints [id=" + id + ", complaintQuery=" + complaintQuery
				+ ", complaintDate=" + complaintDate + ", studentId="
				+ studentId + ", complaintBy=" + complaintBy + "]";
	}

}
