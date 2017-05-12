package com.github.gchapim.adorocinemasearch.util;

import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;


public class UtilDate{
	
	public static GregorianCalendar now() {
		return new GregorianCalendar();
	}
	
	public static GregorianCalendar addMonths(GregorianCalendar date, int months){
		
		date.add(GregorianCalendar.MONTH, months);
		return date;
	}
	
	
	public static GregorianCalendar fullDatetoGregorianCalendar(String fullDate){
		String date[] = StringUtils.remove(fullDate,"de ").split(" ");
		if(Util.filled(date) && date.length >= 3){
			String stringDay = date[0];
			String stringMonth = date[1].toLowerCase();
			String stringYear = date[2];
			
			if(Util.isInteger(stringDay) && Util.isInteger(stringYear)){
				Integer day = Integer.valueOf(stringDay);
				Integer year = Integer.valueOf(stringYear);
				
				Integer month = null;
				if(stringMonth.equals("janeiro")){
					month=0;
				}else if(stringMonth.equals("fevereiro")){
					month=1;
				}else if(stringMonth.equals("março") || stringMonth.equals("marco")){
					month=2;
				}else if(stringMonth.equals("abril")){
					month=3;
				}else if(stringMonth.equals("maio")){
					month=4;
				}else if(stringMonth.equals("junho")){
					month=5;
				}else if(stringMonth.equals("julho")){
					month=6;
				}else if(stringMonth.equals("agosto")){
					month=7;
				}else if(stringMonth.equals("setembro")){
					month=8;
				}else if(stringMonth.equals("outubro")){
					month=9;
				}else if(stringMonth.equals("novembro")){
					month=10;
				}else if(stringMonth.equals("dezembro")){
					month=11;
				}else{
					return null;
				}
				
				GregorianCalendar calendar = new GregorianCalendar(year, month, day);
				return calendar;
			}
		}
		return null;
	}
}