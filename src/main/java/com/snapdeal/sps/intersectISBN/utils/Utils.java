package com.snapdeal.sps.intersectISBN.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Set;

public class Utils {

	public static String formatTimeStamp(String timeStamp){
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("yyyyMMdd").parse(timeStamp));
		} catch (ParseException e) {
			//e.printStackTrace();
			return timeStamp;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return new SimpleDateFormat("dd/MM/yyyy").format(calendar.getTime());
	}



	public static String setToString(Set<String> set){

		if(set.size() == 0)
			return "";
		StringBuffer sb = new StringBuffer();
		for(String str : set)
			sb.append(str + ", ");

		return sb.toString().substring(0, sb.length() - 2);
	}
	
	

}
