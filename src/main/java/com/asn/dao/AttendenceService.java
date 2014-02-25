package com.asn.dao;

import java.util.Date;
import java.util.List;

import com.asn.model.Attendence;
import com.asn.model.AttendenceSheet;

public interface AttendenceService {
	
	Long saveAttendence(Attendence attendence);
	void updateAttendence(Attendence attendence);
	void deleteAttendence(Attendence attendence);
	Attendence getAttendence(Long id);
	List listAttendence();
	Attendence getAttendence(Long studentId, Date attnDate);
	List<Attendence> getAttendence(Long uid, Date fromDate, Date toDate);
	AttendenceSheet getAttendenceSheet(AttendenceSheet attendenceSheet);
}
