package com.koreaIT.BAM.Util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Util {
	public static void getRrgDate(){
		LocalTime now = LocalTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분 ss초");          
		System.out.println(formatter);   
	}
      
}
