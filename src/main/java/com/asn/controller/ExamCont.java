package com.asn.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.asn.dao.CourseService;
import com.asn.dao.ExamDetailsService;
import com.asn.dao.PersonalService;
import com.asn.model.Exam;
import com.asn.model.ExamDetails;
import com.asn.model.PersonalDetails;
@Controller
@RequestMapping("/admin/exam")
public class ExamCont {
	private static final Logger logger = Logger.getLogger(Account.class);
	@Autowired private CourseService courseService;	
	@Autowired private PersonalService personalService;
	@Autowired private ExamDetailsService examDetailsService;
	
	@RequestMapping(value="/addExamDetails", method=RequestMethod.GET)
	public String showAddExamDetails(Model model){
		logger.info("Get came to /admin/addExamDetails");
		model.addAttribute("examDetModel", new ExamDetails());		
		return "addExamDet";
	}

	@RequestMapping(value="/addExamDetails", method=RequestMethod.POST)
	public String processAddExamDetails(@Valid @ModelAttribute("examDetModel")ExamDetails examDetails, BindingResult result, SessionStatus status, ModelMap model){
		logger.info("Post came to /admin/addExamDetails");
		if(examDetails.getObtainMark()==null)
			result.rejectValue("obtainMark", "required.obtainmark");
		if(result.hasErrors())
			return "addExamDet";
		examDetailsService.save(examDetails);
		logger.info("Successfully saved exam details.");
		status.setComplete();
		
		model.addAttribute("examDetModel", new ExamDetails());		
		return "addExamDet";
	}

	@RequestMapping(value="/addExam",method=RequestMethod.GET)
	public String showAddExam(Model model){
		logger.info("Get came to /admin/addExam");
		model.addAttribute("examModel", new Exam());		
		return "addExam";
	}

	@RequestMapping(value="/addExam",method=RequestMethod.POST)
	public String processAddExam(@ModelAttribute("examModel")Exam exam, BindingResult result, SessionStatus status, ModelMap model){
		logger.info("Post came to /admin/addExam");
		logger.info(exam);
		if(!result.hasErrors() && exam!=null){						
			model.addAttribute("succ", examDetailsService.addExam(exam));
			logger.info("exam successfully saved.");
			status.setComplete();			
		}
		return "addExam";
	}

	@RequestMapping(value="/deleteExam/{id}",method=RequestMethod.GET)
	public String processAddExam(@PathVariable("id")Long id, Model model){
		logger.info("Post came to /admin/addExam");
		logger.info(id);
		if(id!=null){
			examDetailsService.deleteExam(examDetailsService.getExam(id));
			logger.info("exam successfully deleted.");			
		}
		return "addExam";
	}
	
	@RequestMapping(value="/getExam", method=RequestMethod.POST)
	public @ResponseBody ExamDetails getExamDetls(@RequestBody ExamDetails examDetails, BindingResult result){
		logger.info("Post ajax came to /admin/getExam");		
		if(!result.hasErrors()){
			Long id = examDetails.getExamId();
		logger.info("id:"+id);
		
		if(id!=null) {
			examDetails.setExam(examDetailsService.getExam(id));
			examDetails.setCourses(courseService.getCourses(examDetails.getExam().getCourseId()));
			examDetails.setStudentList(personalService.getPersonalInfoByQuery("FROM "+PersonalDetails.class.getName()+" where courseId ="+examDetails.getCourses().getId()));
		}}
		return examDetails;
	}
	
	@ModelAttribute("allExams")
	public List getAllExamDetails(){
		return examDetailsService.list();
	}
	@ModelAttribute("exams")
	public List getExams(){
		return examDetailsService.listExams();
	}
	@ModelAttribute("semesters")
	public List getSemesters(){
		List l = new ArrayList<Integer>();
		for(int i=1; i<6; i++)
			l.add(i);
		return l;
	}

	@ModelAttribute("courses")
	public List getCourses(){
		return courseService.listCourses();
	}
}
