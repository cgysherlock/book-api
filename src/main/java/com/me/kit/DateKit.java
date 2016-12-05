package com.me.kit;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateKit {
	
	/**
	 * 根据月份判断第几季度
	 * @param month
	 * @return
	 */
	public static int getQuarterByMonth(int month){
		int quarter = 0;
		switch (month) {  
        case Calendar.JANUARY:  
        case Calendar.FEBRUARY:  
        case Calendar.MARCH:  
        	quarter = 1;  
            break;  
        case Calendar.APRIL:  
        case Calendar.MAY:  
        case Calendar.JUNE:  
        	quarter = 2;  
            break;  
        case Calendar.JULY:  
        case Calendar.AUGUST:  
        case Calendar.SEPTEMBER:  
        	quarter = 3;  
            break;  
        case Calendar.OCTOBER:  
        case Calendar.NOVEMBER:  
        case Calendar.DECEMBER:  
        	quarter = 4;  
            break;  
        default:  
            break;  
        }  
		return quarter;
	}

	
	/**
	 * 获得这个月有几天
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getDaysByMonth(int year,int month) {
		int days = 0;
		if((year%4==0&&year%100!=0)||year%400==0){
			switch (month) {  
	        case 2:   
	        	days = 28;  
	            break;  
	        case 4:  
	        case 6:  
	        case 9:  
	        case 11:  
	        	days = 30;  
	            break;  
	        case 1:  
	        case 3:  
	        case 5:  
	        case 7: 
	        case 8: 
	        case 10: 
	        case 12: 
	        	days = 31;  
	            break;  
	        default:  
	            break;  
	        }
		}else{
			switch (month) {  
	        case 2:   
	        	days = 29;  
	            break;  
	        case 4:  
	        case 6:  
	        case 9:  
	        case 11:  
	        	days = 30;  
	            break;  
	        case 1:  
	        case 3:  
	        case 5:  
	        case 7: 
	        case 8: 
	        case 10: 
	        case 12: 
	        	days = 31;  
	            break;  
	        default:  
	            break;  
	        }	
		}
		return days;
	}
	
	
	/**
	 * 获得这个月有几天
	 * @param yearMonth(yyyy-mm)
	 * @return
	 */
	public static int getDaysByMonth(String yearMonth) {
		int index = yearMonth.indexOf("-");
		int year = Integer.parseInt(yearMonth.substring(0, index));
		int month = Integer.parseInt(yearMonth.substring(index+1));
		int days = 0;
		if((year%4==0&&year%100!=0)||year%400==0){
			switch (month) {  
	        case 2:   
	        	days = 28;  
	            break;  
	        case 4:  
	        case 6:  
	        case 9:  
	        case 11:  
	        	days = 30;  
	            break;  
	        case 1:  
	        case 3:  
	        case 5:  
	        case 7: 
	        case 8: 
	        case 10: 
	        case 12: 
	        	days = 31;  
	            break;  
	        default:  
	            break;  
	        }
		}else{
			switch (month) {  
	        case 2:   
	        	days = 29;  
	            break;  
	        case 4:  
	        case 6:  
	        case 9:  
	        case 11:  
	        	days = 30;  
	            break;  
	        case 1:  
	        case 3:  
	        case 5:  
	        case 7: 
	        case 8: 
	        case 10: 
	        case 12: 
	        	days = 31;  
	            break;  
	        default:  
	            break;  
	        }	
		}
		return days;
	}
	
	/**
	 * 获得当前时间 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getTime(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = format.format(calendar.getTime());
		return time;
	}
	
	/**
	 * 获得当前时间 格式：yyyyMMddHHmmss
	 * @return
	 */
	public static String getStrTime(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(calendar.getTime());
		return time;
	}
	
	/**
	 * 获得今天的日期（yyyy-MM-dd）
	 * @return
	 */
	public static String getToday(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String time = format.format(calendar.getTime());
		return time;
	}
	
	/**
	 * 获得昨天的日期（yyyy-MM-dd）
	 * @return
	 */
	public static String getYesterday(){
		Calendar   cal   =   Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
		System.out.println(yesterday);
		return yesterday;
	}
	
	/**
	 * 获得这是今年的第几星期
	 * @return
	 */
	public static int getWeek(){
		int week = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR)-1;
		return week;
	}

	/**
	 * 获得今天是几年几月（yyyy-MM）
	 * @return
	 */
	public static String getMonth(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		String time = format.format(calendar.getTime());
		return time;
	}
	
	/**
	 * 获得今天是几年（yyyy）
	 * @return
	 */
	public static String getYear(){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String time = format.format(calendar.getTime());
		return time;
	}
	
}
