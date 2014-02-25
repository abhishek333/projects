package com.asn.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.asn.dao.CourseService;
import com.asn.dao.PersonalService;
import com.asn.model.PersonalDetails;
import com.asn.util.Util;

@Controller
@RequestMapping("/user/edit")
public class EditUser {
	
	private static final Logger logger = Logger.getLogger(EditUser.class);
	@Autowired
	private PersonalService personalService;
	@Autowired private CourseService courseService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String editForm(Principal principal, Model model)
	{
		logger.info(principal.getName()+" profile going to edit.");		
		PersonalDetails p = personalService.getPersonalInfo(principal.getName());		
		model.addAttribute("personalDetails", p);		
		return "auth/edit";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String processEditForm(@Valid PersonalDetails personalDetails, BindingResult result, SessionStatus status, ModelMap model)
	{		
		if(!personalDetails.getProfilePhoto().getOriginalFilename().isEmpty())
		{			
			Util.savePhoto(personalDetails.getProfilePhoto(), "F:\\temp\\profilePhoto\\");
			personalDetails.setImageName(personalDetails.getProfilePhoto().getOriginalFilename());
		}
		personalService.updatePersonalInfo(personalDetails);
		status.setComplete();
		model.addAttribute("msg", "Your profile successfully updated.");
		return "message";
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
