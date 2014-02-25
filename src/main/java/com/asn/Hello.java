package com.asn;

import java.security.Principal;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.asn.dao.AttendenceService;
import com.asn.dao.ComplaintsService;
import com.asn.dao.CourseService;
import com.asn.dao.ExamDetailsService;
import com.asn.dao.PersonalService;
import com.asn.dao.StudentCourseFeesService;
import com.asn.model.AttendenceSheet;
import com.asn.model.Complaints;
import com.asn.model.PersonalDetails;
import com.asn.util.Util;

@Controller
public class Hello {
	private static final Logger logger = Logger.getLogger(Hello.class);
	@Autowired private PersonalService personalService;
	@Autowired private AttendenceService attendenceService;
	@Autowired private StudentCourseFeesService sFeesService;
	@Autowired private ComplaintsService complaintsService;
	@Autowired private ExamDetailsService examDetailsService;
	@Autowired private CourseService courseService;
	
	@RequestMapping(value="/")
	public ModelAndView goToHelloPage(Principal principal) {
		logger.info("Get came to Project root");
		ModelAndView view = new ModelAndView();					
		if(principal!=null){
			if(principal.getName().equals("admin"))
				view.setViewName("adminHome");
			else {
				setUserDetails(principal, view);
				view.setViewName("home");
			}
		}
		else {
			view.setViewName("auth/login");
		}					
		return view;
	}
	
	private void setUserDetails(Principal principal, ModelAndView view) {
		PersonalDetails p = personalService.getPersonalInfo(principal.getName());
		p.setComplaints(complaintsService.getByStudentId(p.getId()));
		PersonalDetails pp = null;
		for(Complaints ct : p.getComplaints()){
			pp = personalService.getPersonalInfo(ct.getStudentId());
			if(pp!=null)
			ct.setComplainTo(pp.getFirstName()+" "+pp.getLastName()+"("+pp.getStudentId()+")");
		}
		p.setCourses(courseService.getCourses(p.getCourseId()));
		p.setStudentCourseFees(sFeesService.get(p.getId()));
		p.getStudentCourseFees().setBalanceAmount(p.getStudentCourseFees().getFullFees() - p.getStudentCourseFees().getPaidAmount());
		p.setExamDetails(examDetailsService.getExamDetailsByStdId(p.getId()));
		view.addObject("pr", p);
		Calendar cal = Calendar.getInstance();
		AttendenceSheet as = new AttendenceSheet();
		as.setStudentEmail(principal.getName());
		as.setYear(cal.get(Calendar.YEAR));
		AttendenceSheet att = attendenceService.getAttendenceSheet(as);		
		int totalPnt = 0;
		int totalAbnt = 0;
		if(att!=null && att.getPresentAttendence()!=null && att.getAbsentAttendence()!=null){
			totalPnt = att.getPresentAttendence().get(Util.getMonthCodeName(cal.get(Calendar.MONTH)));
			totalAbnt = att.getAbsentAttendence().get(Util.getMonthCodeName(cal.get(Calendar.MONTH)));
		}
		view.addObject("attTotalDay", totalPnt+totalAbnt);
		view.addObject("totalPnt", totalPnt);
		view.addObject("totalAbnt", totalAbnt);
		Util.setCurrentUser(p);
		logger.info("Student details successfully set.");
	}

	@RequestMapping(value="/excp")
	public String exceptionTest() throws Exception
	{
		if(true){
			throw new Exception("This is a exception");			
		}
		return "home";
	}
	
}
