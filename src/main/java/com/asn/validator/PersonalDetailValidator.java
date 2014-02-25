package com.asn.validator;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.asn.dao.UserService;
import com.asn.model.PersonalDetails;
@Component
public class PersonalDetailValidator implements Validator {
	private static final Logger logger = Logger.getLogger(PersonalDetailValidator.class);
	@Autowired private UserService userService;
	@Override
	public boolean supports(Class arg0) {		
		return PersonalDetails.class.isAssignableFrom(arg0);
	}

	@Override
	public void validate(Object object, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "user.password", "required.password");
		ValidationUtils.rejectIfEmpty(errors, "gender", "required.gender");
		ValidationUtils.rejectIfEmpty(errors, "mobile", "required.mobNum");
		ValidationUtils.rejectIfEmpty(errors, "email", "required.mobNum");
		ValidationUtils.rejectIfEmpty(errors, "lastName", "required.lastName");
		ValidationUtils.rejectIfEmpty(errors, "firstName", "required.firstName");
		ValidationUtils.rejectIfEmpty(errors, "dateOfBirth", "required.dateOfBirth");
		ValidationUtils.rejectIfEmpty(errors, "city", "required.city");
		ValidationUtils.rejectIfEmpty(errors, "state", "required.state");
		ValidationUtils.rejectIfEmpty(errors, "studentId", "required.studentId");
		ValidationUtils.rejectIfEmpty(errors, "admissionNum", "required.admissionNum");		
		ValidationUtils.rejectIfEmpty(errors, "section", "required.section");
		ValidationUtils.rejectIfEmpty(errors, "fatherName", "required.fatherName");
		
		PersonalDetails	p = (PersonalDetails)object;
		if(p.getCourseId()==0)
				errors.rejectValue("course", "required.course");
		if("NONE".equals(p.getSection()))
			errors.rejectValue("section", "required.section");
		if(userService.findByEmail(p.getEmail())!=null)
			errors.rejectValue("email", "validation.exists");
	}

}
