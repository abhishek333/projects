package com.asn.controller;

import java.security.Principal;
import java.util.Date;

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

import com.asn.dao.ComplaintsService;
import com.asn.dao.PersonalService;
import com.asn.model.Complaints;
import com.asn.model.PersonalDetails;

@Controller
@RequestMapping("/user/complaints")
public class ComplaintsCont {
	private static final Logger logger = Logger.getLogger(ComplaintsCont.class);
	@Autowired private ComplaintsService complaintsService;
	@Autowired private PersonalService personalService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String showComplaintsForm(Model model){
		logger.info("GET came to /complaints");
		model.addAttribute("allStud", personalService.getPersonalInfoByQuery("FROM "+PersonalDetails.class.getName()));
		model.addAttribute("complaintModel", new Complaints());
		return "complaintsForm";
	}

	@RequestMapping(value="/{stdId}",method=RequestMethod.GET)
	public String showComplaintsFormByIndividual(@PathVariable("stdId")Long stdId, Model model){
		logger.info("GET came to /complaints/{stdId}");
		logger.info("stdId: "+stdId);
		model.addAttribute("allStud", personalService.getPersonalInfoByQuery("FROM "+PersonalDetails.class.getName()));
		PersonalDetails pd = personalService.getPersonalInfo(stdId);
		Complaints compl = new Complaints();
		compl.setStudentId(pd.getId());
		model.addAttribute("complaintModel", compl);
		return "complaintsForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String processComplaintsForm(@ModelAttribute("complaintModel")Complaints complaints, BindingResult result, SessionStatus status, Principal principal, ModelMap model){
		logger.info("POST came to /complaints");
		if(complaints!=null) {			
			complaints.setComplaintBy(principal.getName());
			complaints.setComplaintDate(new Date());		
			model.addAttribute("msg", "Successfully registered your complaint with id: "+complaintsService.save(complaints));
		}
		else
			model.addAttribute("msg", "Sorry, some internal error occured while registering your complaint, try again..");
		return "message";
	}

	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String viewComplaints(Model model){
		logger.info("Get came to /complaints/view");
		model.addAttribute("compList", complaintsService.list());
		return "viewComplaints";
	}
}
