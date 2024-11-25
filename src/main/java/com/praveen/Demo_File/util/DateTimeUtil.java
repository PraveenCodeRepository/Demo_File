package com.praveen.Demo_File.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
   
	public static String formatDataTime(LocalDateTime localDateTime) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy :HH:mm:ss");
		 String format = localDateTime.format(formatter);
		 return format;
	}
}

