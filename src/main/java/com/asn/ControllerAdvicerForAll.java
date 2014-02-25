package com.asn;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdvicerForAll {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView exception(Exception e)
	{
		ModelAndView mView = new ModelAndView("exception");
		mView.addObject("name", e.getClass().getSimpleName());		
		mView.addObject("message", e.getMessage());
		e.printStackTrace();
		return mView;		
	}
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd-MM-yyyy"), true));
	}
	
}
