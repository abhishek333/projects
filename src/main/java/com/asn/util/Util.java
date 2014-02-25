package com.asn.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.asn.model.PersonalDetails;

public class Util {
	private static final Logger logger = Logger.getLogger(Util.class);
	public static String currentUserImageName;
	public static String currentUserFullName;
	/** bindingDateFormat as dd-MM-yyyy*/
	public static SimpleDateFormat bindingDateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	public static List<String> getCountries()
	{
		List<String> cntrLst = new ArrayList<String>();
		for (String countryCode : Locale.getISOCountries()) {

		    Locale obj = new Locale("", countryCode);
		    cntrLst.add(obj.getDisplayCountry());
		}
		return cntrLst;
	}
	
	public static List<String> getCity() {
		List<String> agLst = new ArrayList<String>();
		agLst.add("Bangalore");
		agLst.add("Hydrabad");
		agLst.add("Chenai");
		agLst.add("Kolkata");
		agLst.add("Delhi");
		return agLst;
	}
	
	public static void savePhoto(MultipartFile filea, String path)
	{
		File theDir = new File(path);

		  // if the directory does not exist, create it
		  if (!theDir.exists()) {
			  logger.info("creating directory: " + path);
		    boolean result = theDir.mkdirs();  

		     if(result) {    
		       logger.info("DIR created");  
		     }
		  }
			
			InputStream inputStream = null;
			OutputStream outputStream = null;
			try{
			if (filea.getSize() > 0) {
				inputStream = filea.getInputStream();
				outputStream = new FileOutputStream(path + filea.getOriginalFilename());
				int readBytes = 0;
				byte[] buffer = new byte[8192];
				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
				logger.info("photo uploaded successfully.");		
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resetCurrentUser() {
		currentUserImageName = "";
		currentUserFullName = "";
	}

	public static void setCurrentUser(PersonalDetails personalInfo) {
		if(personalInfo==null)
			return;
		currentUserFullName = personalInfo.getFirstName()+" "+personalInfo.getLastName();
		currentUserImageName = personalInfo.getImageName();
	}
	
	public static String getMonthCodeName(int n)
	{
		switch(n){
		case 0:
			return "Jan";
		case 1:
			return "Feb";
		case 2:
			return "Mar";
		case 3:
			return "Apr";
		case 4:
			return "May";
		case 5:
			return "Jun";
		case 6:
			return "Jul";
		case 7:
			return "Aug";
		case 8:
			return "Sep";
		case 9:
			return "Oct";
		case 10:
			return "Nov";
		case 11:
			return "Dec";
		default:
			return "InvalidMonth";
		}
	}
	private static Calendar mycal = new GregorianCalendar();
	public static int getTotalDaysOfMonth(int year, int month)
	{		
		mycal.set(GregorianCalendar.YEAR, year);
		mycal.set(GregorianCalendar.MONTH, month);
		mycal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		// Get the number of days in that month
		return mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}
}
