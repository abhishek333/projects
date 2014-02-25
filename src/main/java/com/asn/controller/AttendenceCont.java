package com.asn.controller;

import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asn.dao.AttendenceService;
import com.asn.dao.CourseService;
import com.asn.dao.PersonalService;
import com.asn.model.Attendence;
import com.asn.model.AttendenceRefine;
import com.asn.model.AttendenceSheet;
import com.asn.model.PersonalDetails;
import com.asn.util.Util;

@Controller
public class AttendenceCont {
	private static final Logger logger = Logger.getLogger(AttendenceCont.class);
	@Autowired private PersonalService personalService;
	@Autowired private AttendenceService attendenceService;
	@Autowired private CourseService courseService;
	
	@RequestMapping(value="/admin/attendence", method=RequestMethod.GET)
	public String showAttendence(Model model) {		
		model.addAttribute("attendenceRef", new AttendenceRefine());
		model.addAttribute("students", personalService.getPersonalInfoByQuery("FROM "+PersonalDetails.class.getName()));
		return "attendenceForm";
	}

	@RequestMapping(value="/admin/attendence", method=RequestMethod.POST)
	public String refineAttendence(AttendenceRefine attendenceRefine, BindingResult result, Model model) {
		List<PersonalDetails> l = null;
		if(attendenceRefine!=null)
		 l = personalService.getPersonalInfoByQuery("FROM "+PersonalDetails.class.getName()+" where courseId="+attendenceRefine.getCourseId());
		if(l.size()>0)
			for(PersonalDetails p : l)
				p.setAttendence(attendenceService.getAttendence(p.getUserId(), attendenceRefine.getAttendenceDate()));
		model.addAttribute("attendenceRef", attendenceRefine);
		model.addAttribute("students", l);		
		return "attendenceForm";
	}
	
	@RequestMapping(value="/admin/addAttendence", method = RequestMethod.POST)
	public @ResponseBody Attendence addAttendence(@RequestBody Attendence attendence, BindingResult result)
	{	
		if(!result.hasErrors())
		{
			Attendence at = attendenceService.getAttendence(attendence.getStudentId(), attendence.getAttnDate());
			if(at==null)
			{				
				attendence.setId(attendenceService.saveAttendence(attendence));
				logger.info("attence saved.");
			}
			else
			{		
				attendence.setId(at.getId());
				attendenceService.updateAttendence(attendence);
				logger.info("attence updated.");
				attendence.setResult("Successfull");
			}
		}
		else
		{
			attendence.setResult("Sorry some error occured, attendence not added.");			
		}			
				
		return attendence;		
	}
	@ModelAttribute("classes")
	public List getClasses()
	{
		return courseService.listCourses();
	}
	
	@RequestMapping(value="/getAttendenceMap", method=RequestMethod.POST, produces= MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AttendenceSheet attendenceMap(@RequestBody AttendenceSheet attendenceSheet, BindingResult result)
	{
		logger.info("POST came to /getAttendenceMap");
		if(result.hasErrors())
		{
			logger.info("validation failed of attendenceSheet.");
			return attendenceSheet;
		}
		logger.info("id:"+attendenceSheet.getStudentEmail());
		return attendenceService.getAttendenceSheet(attendenceSheet);
	}
}
