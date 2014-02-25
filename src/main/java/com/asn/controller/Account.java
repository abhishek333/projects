package com.asn.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.asn.dao.CourseFeesService;
import com.asn.dao.CourseService;
import com.asn.dao.PersonalService;
import com.asn.dao.RoleService;
import com.asn.dao.StudentCourseFeesService;
import com.asn.dao.UserService;
import com.asn.model.PersonalDetails;
import com.asn.model.Role;
import com.asn.model.StudentCourseFees;
import com.asn.model.User;
import com.asn.util.Util;
import com.asn.validator.PersonalDetailValidator;

@Controller
public class Account {
	
	private static final Logger logger = Logger.getLogger(Account.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;	

	@Autowired
	private PersonalService personalService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private StudentCourseFeesService sFeesService;
	
	@Autowired
	private CourseFeesService courseFeesService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private PersonalDetailValidator personalDetailValidator;
	
/*	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new CustomDateEditor(Util.bindingDateFormat, true));
	}
	*/
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home()
	{
		logger.info("Get /home");
		return "home";
	}

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login()
	{
		logger.info("Get /login");
		return "auth/login";
	}
	
	@RequestMapping(value="/forgotPwd", method=RequestMethod.GET)
	public String forgotPassword(Model model)
	{
		logger.info("Get /forgotPwd");
		model.addAttribute("user", new User());
		return "auth/forgotPwd";
	}
	
	@RequestMapping(value="/forgotPwd", method=RequestMethod.POST)
	public String processForgotPassword(User user, BindingResult result, SessionStatus status, ModelMap model)
	{
		logger.info("Post /forgotPwd");
		if(user.getUsername().isEmpty() || user.getPassword().isEmpty())
		{
			model.addAttribute("ferr", "username and password required.");
			return "auth/forgotPwd";
		}
		User domainUser = userService.findByEmail(user.getUsername());		
		if(domainUser==null)
		{
			model.addAttribute("ferr", "username not registered.");
			return "auth/forgotPwd";
		}
		domainUser.setPassword(user.getPassword());
		userService.updateUser(domainUser);
		status.setComplete();
		//userService.updateUserPassword(domainUser.getId(), encoder.encode(user.getPassword()));
		model.addAttribute("forgotPwd", "Successfully changed your password.");
		return "auth/forgotPwd";
	}

	@RequestMapping(value="/loginfailed", method=RequestMethod.GET)
	public String loginFailed()
	{
		logger.info("Get /loginfailed");
		return "auth/login";
	}

	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout()
	{
		logger.info("Get /logout");
		Util.resetCurrentUser();
		return "auth/logout";
	}

	@RequestMapping(value="/accessDenied", method=RequestMethod.GET)
	public String accessDenied(Model model)
	{
		logger.info("Get /accessDenied");		
		return "accessDenied";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model)
	{
		logger.info("Get /register");
		PersonalDetails personalDetails = new PersonalDetails();
		personalDetails.setGender("M");
		model.addAttribute("personalDetails", personalDetails);
		return "auth/register";
	}

	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegister(@Valid PersonalDetails personalInfo, BindingResult result, SessionStatus status, ModelMap model)
	{
		logger.info("POST /register");
		personalDetailValidator.validate(personalInfo, result);
		if(result.hasErrors())
			return "auth/register"; 
		
		User user = new User();
		user.setActive(true);
		user.setPassword(personalInfo.getUser().getPassword());
		user.setUsername(personalInfo.getEmail());
		if(!personalInfo.getProfilePhoto().getOriginalFilename().isEmpty())
		{			
			Util.savePhoto(personalInfo.getProfilePhoto(), "F:\\temp\\profilePhoto\\");
			personalInfo.setImageName(personalInfo.getProfilePhoto().getOriginalFilename());
		}
		Long userId = userService.createUser(user);
			
		if(userId!=null){				
			roleService.insertRole(new Role(1, userId));
			personalInfo.setUserId(userId);
			//System.out.println("personalInfo: "+personalInfo);
			Long perId = personalService.savePersonalInfo(personalInfo);	
			Long roleId = new Long(roleService.getRole(userId).getId());
			//System.out.println("roleId: "+roleId);
			user.setRole(roleId);				
			user.setPersonalDetails(perId);
			user.setId(userId);			
			//System.out.println(user);			
			userService.updateUser(user);
			StudentCourseFees scf = new StudentCourseFees();
			scf.setCourseId(personalInfo.getCourseId());
			scf.setFullFees(courseFeesService.getCourseFeesByCourseId(personalInfo.getCourseId()).getFullFees());
			scf.setPaidAmount(new Double(0));
			scf.setStudentId(perId);
			sFeesService.save(scf);
		}
		status.setComplete();
		model.addAttribute("message", "Registration successfull.");
		status.setComplete();
		return "auth/login";
	}
	
	@ModelAttribute("countrys")
	public List<String> getCountries()
	{
		return Util.getCountries();
	}
	
	@ModelAttribute("courses")
	public List getCourses()
	{
		return courseService.listCourses();
	}
	
}
