package com.asn.model;

import java.util.Map;

public class AttendenceSheet {
	
	private Integer year;
	private String studentEmail;
	private Map<String, Integer> presentAttendence;
	private Map<String, Integer> absentAttendence;	

	public AttendenceSheet(){}
	

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	public Map<String, Integer> getPresentAttendence() {
		return presentAttendence;
	}


	public void setPresentAttendence(Map<String, Integer> map) {
		this.presentAttendence = map;
	}


	public Map<String, Integer> getAbsentAttendence() {
		return absentAttendence;
	}


	public void setAbsentAttendence(Map<String, Integer> absentAttendence) {
		this.absentAttendence = absentAttendence;
	}


	public Integer getYear() {
		return year;
	}


	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "AttendenceSheet [year=" + year + ", studentEmail="
				+ studentEmail + ", presentAttendence=" + presentAttendence
				+ ", absentAttendence=" + absentAttendence + "]";
	}
	
}

