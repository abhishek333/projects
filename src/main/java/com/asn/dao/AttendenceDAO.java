package com.asn.dao;

import java.util.Date;
import java.util.List;

import com.asn.model.Attendence;

public interface AttendenceDAO {
	
	Long saveAttendence(Attendence attendence);
	void updateAttendence(Attendence attendence);
	void deleteAttendence(Attendence attendence);
	Attendence getAttendence(Long id);
	Attendence getAttendence(Long studentId, Date attnDate);
	List listAttendence();
	List<Attendence> getAttendence(Long uid, Date fromDate, Date toDate);
}
