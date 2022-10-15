package com.rest.api.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public interface ProjectConstant {

	// constant variable

	String SUCCESS_MSG = "SUCCESS";
	String ERR_MSG = "ERROR";
	String RESOURCE_NOT_PRESENT = "Resource Not Present in Database";
	String DELETED_MSG = "Resource Deleted";
	String CREATED_MSG = "Resource Created";
	String UPDATED_MSG = "Resource Updated";
	String DATA_FOUND = "Data Found";
	String DATA_NOT_FOUND = "Data not Found";
	String NOT_PRESENT = "NOT PRESENT";

	// constant method

	static String formattedDateTime(LocalDateTime date) {
		LocalDateTime zonedISTtime = convertUTCtoISTtime(date);
		
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd MMMM yyyy hh:mm:ss a");
		return zonedISTtime.format(myFormatObj).toUpperCase();
	}
	
	static LocalDateTime convertUTCtoISTtime(LocalDateTime date)
	{
		// setting UTC as the timezone
		ZonedDateTime zonedUTCtime = date.atZone(ZoneId.of("UTC"));
		
		// converting to IST
		LocalDateTime zonedISTtime = zonedUTCtime.withZoneSameInstant(ZoneId.of("Asia/Kolkata")).toLocalDateTime();
		
		return zonedISTtime;
	}
}
