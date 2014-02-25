package com.asn.model;

import java.util.Date;

public class AttendenceRefine {
	
	private Date attendenceDate;
	private Long courseId;
	
	public AttendenceRefine() {
	}
	
	public Date getAttendenceDate() {
		return attendenceDate;
	}
	public void setAttendenceDate(Date attendenceDate) {
		this.attendenceDate = attendenceDate;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	
	
}
