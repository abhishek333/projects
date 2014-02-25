package com.asn.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asn.dao.AttendenceDAO;
import com.asn.dao.AttendenceService;
import com.asn.dao.PersonalService;
import com.asn.model.Attendence;
import com.asn.model.AttendenceSheet;
import com.asn.model.PersonalDetails;
import com.asn.util.Util;
@Service
@Transactional
public class AttendenceServiceImpl implements AttendenceService {
	private static final Logger logger = Logger.getLogger(AttendenceServiceImpl.class);
	@Autowired private AttendenceDAO attendenceDAO;
	@Autowired private PersonalService personalService;
	
	@Override
	public Long saveAttendence(Attendence attendence) {
		return attendenceDAO.saveAttendence(attendence);
	}

	@Override
	public void updateAttendence(Attendence attendence) {
		attendenceDAO.updateAttendence(attendence);
	}

	@Override
	public void deleteAttendence(Attendence attendence) {
		attendenceDAO.deleteAttendence(attendence);
	}

	@Override
	public Attendence getAttendence(Long id) {		
		return attendenceDAO.getAttendence(id);
	}

	@Override
	public List listAttendence() {
		return attendenceDAO.listAttendence();
	}

	@Override
	public Attendence getAttendence(Long studentId, Date attnDate) {
		return attendenceDAO.getAttendence(studentId, attnDate);
	}

	@Override
	public List<Attendence> getAttendence(Long uid, Date fromDate, Date toDate) {
		return attendenceDAO.getAttendence(uid, fromDate, toDate);
	}
	
	@Override
	public AttendenceSheet getAttendenceSheet(AttendenceSheet attendenceSheet) {
		PersonalDetails p = personalService.getPersonalInfo(attendenceSheet.getStudentEmail());		
		if(p==null){
			logger.info("user id not found..");
			return attendenceSheet;
		}
		HashMap<String, Integer> prMap = new HashMap<String, Integer>();
		HashMap<String, Integer> abMap = new HashMap<String, Integer>();
		Calendar c = Calendar.getInstance();		
		Calendar c2 = Calendar.getInstance();		
		Date fromDate = null; 
		Date toDate = null;
				
		List<Attendence> l = null;
		
		for(int i=Calendar.JANUARY; i<=Calendar.DECEMBER; i++)
		{
			c.set(Calendar.YEAR, attendenceSheet.getYear());
			c.set(Calendar.MONTH,i);
			c.set(Calendar.DAY_OF_MONTH, 1);
			fromDate = c.getTime();
			c2.set(Calendar.YEAR,attendenceSheet.getYear());
			c2.set(Calendar.MONTH,i);
			//logger.info("lastDate:"+Util.getTotalDaysOfMonth(attendenceSheet.getYear(), i));
			int totDy = Util.getTotalDaysOfMonth(attendenceSheet.getYear(), i);
			c2.set(Calendar.DAY_OF_MONTH, totDy);
			toDate = c2.getTime();					
			//logger.info("id:"+p.getUserId()+" from:"+fromDate+" & toDate:"+toDate);
			l = getAttendence(p.getUserId(), fromDate, toDate);
			int pCount = 0;
			for(Attendence en : l)			
				if(en.getIsPresent())
					pCount++;
			//logger.info("list size:"+l.size());
			prMap.put(Util.getMonthCodeName(i), pCount);
			abMap.put(Util.getMonthCodeName(i), totDy-pCount);
		}
		attendenceSheet.setPresentAttendence(prMap);
		attendenceSheet.setAbsentAttendence(abMap);
		return attendenceSheet;
	}
}
