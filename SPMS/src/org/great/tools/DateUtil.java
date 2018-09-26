package org.great.tools;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	//获取当天时间
	public static String getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String time=df.format(cal.getTime());
		return time;
	}
	

}
