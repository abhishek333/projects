package com.asn.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.asn.dao.CourseFeesService;
import com.asn.dao.CourseService;
import com.asn.dao.PersonalService;
import com.asn.dao.StudentCourseFeesService;
import com.asn.model.CourseFeesStructure;
import com.asn.model.Courses;
import com.asn.model.PersonalDetails;
import com.asn.model.StudentCourseFees;

@Controller
public class AdminCont {
	private static final Logger logger = Logger.getLogger(AdminCont.class);
	
	@Autowired private CourseFeesService courseFeesService;
	@Autowired private CourseService courseService;	
	@Autowired private PersonalService personalService;
	@Autowired private StudentCourseFeesService sFeesService;
	@RequestMapping(value="/admin/addcourse", method=RequestMethod.GET)
	public String showCourseForm(Model model) {
		model.addAttribute("course", new Courses());
		model.addAttribute("allCourses", courseService.listCourses());
		return "addCourse";
	}

	@RequestMapping(value="/admin/addcourse", method=RequestMethod.POST)
	public String prcoessCourseForm(@ModelAttribute("course")Courses course, BindingResult result, SessionStatus status, ModelMap model) {
		if(course.getCourseName().isEmpty() || course.getCourseName().length() < 1)		
			result.rejectValue("courseName", "required.course");
		if(courseService.getCourses(course.getCourseName())!=null){
			System.out.println("course already exists.");
			result.rejectValue("courseName", "required.courseExists");
		}
		if(result.hasErrors())
			return "addCourse";
		model.addAttribute("succ", courseService.saveCourse(course));	
		model.addAttribute("allCourses", courseService.listCourses());
		return "addCourse";
	}
	
	@RequestMapping(value="/admin/updateCourse", method=RequestMethod.GET)
	public String updateCourse2( Model model) {
		model.addAttribute("allCourses", courseService.listCourses());		
		return "updateCourse";
	}

	@RequestMapping(value="/admin/updateCourse/{id}", method=RequestMethod.GET)
	public String updateCourse(@PathVariable("id") Long id, Model model) {		
		if(id!=null)
			model.addAttribute("course", courseService.getCourses(id));
		return "updateCourse";
	}
	
	@RequestMapping(value="/admin/updateCourse", method=RequestMethod.POST)
	public String processUpdateCourse(Courses courses, BindingResult result, SessionStatus status, ModelMap model) {
		if(courses.getCourseName().isEmpty() || courses.getCourseName().length() < 1)		
			result.rejectValue("courseName", "required.course");		
		if(result.hasErrors())
			return "addCourse";
		courseService.updateCourse(courses);
		status.setComplete();
		model.addAttribute("succupd", courses.getId());
		model.addAttribute("allCourses", courseService.listCourses());
		return "updateCourse";
	}

	@RequestMapping(value="/admin/deleteCourse", method=RequestMethod.GET)
	public String deleteCourse2(Model model) {		
		model.addAttribute("allCourses", courseService.listCourses());
		return "deleteCourse";
	}

	@RequestMapping(value="/admin/deleteCourse/{id}", method=RequestMethod.GET)
	public String deleteCourse(@PathVariable("id") Long id, Model model) {
		courseService.deleteCourse(courseService.getCourses(id));
		model.addAttribute("succdel", "Successfully deleted.");
		model.addAttribute("allCourses", courseService.listCourses());
		logger.info("Successfully deleted course.");
		return "deleteCourse";
	}

	@RequestMapping(value="/admin/courseFees", method=RequestMethod.GET)
	public String showCourseFees(Model model) {
		logger.info("Get came to /admin/courseFees");
		model.addAttribute("courseFees", courseFeesService.listAllCourseFees());		
		return "courseFees";
	}

	@RequestMapping(value="/admin/addCourseFees", method=RequestMethod.GET)
	public String showAddCourseFees(Model model) {
		logger.info("Get came to /admin/addCourseFees");		
		model.addAttribute("courseFeeModel", new CourseFeesStructure());
		model.addAttribute("courseFees", courseFeesService.listAllCourseFees());
		
		return "addCourseFees";
	}
	@RequestMapping(value="/admin/addCourseFees", method=RequestMethod.POST)
	public String processAddCourseFees(@ModelAttribute("courseFeeModel") CourseFeesStructure courseFeesStructure, BindingResult result, SessionStatus status, ModelMap model) {
		logger.info("POST came to /admin/addCourseFees");
		if(courseFeesStructure!=null) {
			CourseFeesStructure cf = courseFeesService.getCourseFeesByCourseId(courseFeesStructure.getCourseId());
			if(cf!=null){
				cf.setFullFees(courseFeesStructure.getFullFees());
				courseFeesService.updateCourseFees(cf);
			}
			else
				courseFeesService.saveCourseFess(courseFeesStructure);
		}
		else
			logger.info("courseFees is null.");
					
		status.setComplete();
		model.addAttribute("courseFees", courseFeesService.listAllCourseFees());
		return "addCourseFees";
	}
	
	@RequestMapping(value="/admin/stdCourseFeesForm", method = RequestMethod.GET)
	public String showStudentCourseFeesForm(Model model) {
		logger.info("Get came to /admin/stdCourseFeesForm");
		List<PersonalDetails> l = personalService.getPersonalInfoByQuery("FROM "+PersonalDetails.class.getName());
		for(PersonalDetails p: l) {			
			p.setStudentCourseFees(sFeesService.get(p.getId()));
			p.getStudentCourseFees().setBalanceAmount(p.getStudentCourseFees().getFullFees()-p.getStudentCourseFees().getPaidAmount());
		}
		model.addAttribute("listStudents", l);
		return "stdCourseFeesForm";
	}

	@RequestMapping(value="/admin/stdCourseFeesForm/{stdId}/{courseId}", method = RequestMethod.GET)
	public String showStudentFeesForm(@PathVariable("stdId")Long id, @PathVariable("courseId")Long courseId, Model model) {
		logger.info("Get came to /admin/stdCourseFeesForm");
		if(id==null || courseId==null){
			logger.info("student id or course id is null");
			model.addAttribute("err", "student id or course id is null");
			return "stdCourseFeesFormPaymnet";
		}
		StudentCourseFees scf = null;
		if(sFeesService.get(id)==null) {
			logger.info("scf is not there in db");
			scf = new StudentCourseFees();
			scf.setStudentId(id);
			scf.setCourses(courseService.getCourses(courseId));
			scf.setPers(personalService.getPersonalInfo(id));			
			model.addAttribute("stdFeesForm", scf);
		}
		else {
			logger.info("scf going to retrive from db..");
			scf = sFeesService.get(id);			
			model.addAttribute("stdFeesForm", scf);
		}
		scf.setBalanceAmount(scf.getFullFees()-scf.getPaidAmount());
		return "stdCourseFeesFormPaymnet";
	}

	@RequestMapping(value="/admin/stdCourseFeesForm", method = RequestMethod.POST)
	public String processStudentFeesForm(@ModelAttribute("stdFeesForm")StudentCourseFees studentCourseFees, BindingResult result, SessionStatus status, ModelMap model) {
		logger.info("Post came to /admin/stdCourseFeesForm");
		logger.info(studentCourseFees);
		if(studentCourseFees.getPayingAmount()<0 || studentCourseFees.getPayingAmount() > studentCourseFees.getBalanceAmount())
			result.rejectValue("payingAmount", "err.paying");
		if(result.hasErrors()) {
			return "stdCourseFeesFormPaymnet";
		}
		StudentCourseFees sc = sFeesService.get(studentCourseFees.getStudentId());
		if(sc!=null) {
			sc.setPaidAmount(sc.getPaidAmount()+studentCourseFees.getPayingAmount());
			sFeesService.update(sc);
		} 
		else
			sFeesService.save(studentCourseFees);
		model.addAttribute("listStudents", personalService.getPersonalInfoByQuery("FROM "+PersonalDetails.class.getName()));
		return "redirect:/admin/stdCourseFeesForm";
	}
	
	@ModelAttribute("courses")
	public List getCourses() {
		return courseService.listCourses();
	}
	
}
