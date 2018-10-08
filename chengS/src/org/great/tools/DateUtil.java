package org.great.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	// 获取当天时间
	public static String getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String time = df.format(cal.getTime());
		return time;
	}

	// 获取这个月开始时间
	public static String getBeginDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(getNowYear(), getNowMonth() - 1, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00");
		String time = df.format(cal.getTime());
		return time;
	}

	// 获取上个月开始时间
	public static String getBeginDayOfLastMonth() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.MONTH, -1);
		calendar.set(calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00");
		String time = df.format(calendar.getTime());
		return time;
	}

	// 获取今年是哪一年
	public static Integer getNowYear() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return Integer.valueOf(gc.get(1));
	}

	// 获取本月是哪一月
	public static int getNowMonth() {
		Date date = new Date();
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		return gc.get(2) + 1;
	}

	//的到有效期后的日期
	public static String plusDay(int num, String newDate) {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = null;
		String DateToStr2 = "";
		try {
			d1 = format1.parse(newDate);
			Calendar cl = Calendar.getInstance();
			cl.setTime(d1);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String DateToStr = format.format(d1);
			System.out.println("当前时间 : " + DateToStr);
			cl.setTime(d1);
			cl.add(Calendar.DATE, num);
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
			DateToStr2 = format2.format(cl.getTime());
			System.out.println("过期时间为的时间为： " + DateToStr2);
		} catch (Exception e) {
			DateToStr2 = "未知错误";
		}
		return DateToStr2;
	}

		//比较两个时间相加
		 public static int daysBetween(String smdate,String bdate){
		    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		        Calendar cal = Calendar.getInstance();  
		        try {
					cal.setTime(sdf.parse(smdate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        long time1 = cal.getTimeInMillis();               
		        try {
					cal.setTime(sdf.parse(bdate));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
		        long time2 = cal.getTimeInMillis();       
		        long between_days=(time2-time1)/(1000*3600*24);
		          
		       return Integer.parseInt(String.valueOf(between_days));   
		    }
		//获取当天时间
			public static String getNowDay() {
				Calendar cal = new GregorianCalendar();
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				String time=df.format(cal.getTime());
				return time;
			}
		 
		 
	}

