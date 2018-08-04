/**
 * 
 */
package com.ecp.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.StringUtils;


/**
 * @author liaojinhua
 *
 */
public class DateUtil {
	
	public static Date parseDate(String dateStr,String format)throws ParseException
	{
		SimpleDateFormat sf = new SimpleDateFormat(format); 
		return sf.parse(dateStr);
	}
	
	public static String toString(Date d,String format){
		if(d==null){
			d=new Date();
		}
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(d);
	}
	/**
	 * 日期的开始时间
	 * @param date 当前时间
	 * @param offset 当前日期的偏移天数
	 * @return
	 * @throws ParseException
	 */
	public  static Date getDayFirstDate(Date date,int offset) {    
		Calendar scalendar = new GregorianCalendar();    
		scalendar.setTime(date);    
		//scalendar.add(Calendar.MONTH, offset);    
		scalendar.add(Calendar.DATE, offset);   
		scalendar.set(Calendar.HOUR_OF_DAY,0);  
		scalendar.set(Calendar.MINUTE,0);
		scalendar.set(Calendar.SECOND,0);
		return scalendar.getTime();    
	}
	/**
	 * 月份的开始时间
	 * @param date 
	 * @param offset
	 * @return
	 * @throws ParseException
	 */
	public  static Date getMonthFirstDate(Date date,int offset){    
		Calendar scalendar = new GregorianCalendar();    
		scalendar.setTime(date);    
		scalendar.add(Calendar.MONTH, offset);    
		scalendar.set(Calendar.DATE, 1);   
		scalendar.set(Calendar.HOUR,0);  
		scalendar.set(Calendar.MINUTE,0);
		scalendar.set(Calendar.SECOND,0);
		return scalendar.getTime();    
	}
	public  static Date getYearDate(Date date,int year){    
		Calendar scalendar = new GregorianCalendar();    
		scalendar.setTime(date);    
		scalendar.add(Calendar.YEAR, year);    
		//scalendar.set(Calendar.DATE, 1);   
		scalendar.set(Calendar.HOUR,0);  
		scalendar.set(Calendar.MINUTE,0);
		scalendar.set(Calendar.SECOND,0);
		return scalendar.getTime();    
	} 
	
	public static Date getDefineDay(Date date,int day){    
		Calendar scalendar = new GregorianCalendar();    
		scalendar.setTime(date);    
		  
		scalendar.set(Calendar.DATE, day);   
		scalendar.set(Calendar.HOUR,0);  
		scalendar.set(Calendar.MINUTE,0);
		scalendar.set(Calendar.SECOND,0);
		return scalendar.getTime();    
	} 
	
	public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"日", "一", "二", "三", "四", "五", "六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0){
            w = 0;
        }
        return weekDays[w];
    }

	
	public  static Date getDayEndDate(Date date,int offset) {    
		Calendar scalendar = new GregorianCalendar();    
		scalendar.setTime(date);    
		scalendar.add(Calendar.DATE, offset);  
		
		    
		scalendar.set(Calendar.HOUR_OF_DAY,23);  
		scalendar.set(Calendar.MINUTE,59);
		scalendar.set(Calendar.SECOND,59);
		return scalendar.getTime();    
	}
	public  static Date getMonthEndDate(Date date,int offset) {    
		Calendar scalendar = Calendar.getInstance();    
		scalendar.setTime(date);    
		scalendar.add(Calendar.MONTH, offset);  
		scalendar.set(Calendar.DAY_OF_MONTH, scalendar.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		scalendar.set(Calendar.HOUR,23);  
		scalendar.set(Calendar.MINUTE,59);
		scalendar.set(Calendar.SECOND,59);
		return scalendar.getTime();    
	}
	
	public static Date getMonthLastDay() {      
	    Calendar calendar = Calendar.getInstance();      
	    calendar.set(Calendar.DAY_OF_MONTH, calendar      
	            .getActualMaximum(Calendar.DAY_OF_MONTH));      
	    return calendar.getTime();      
	}  
	
	public static Date getDay(Date date ,int offset) {      
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(date);    
	    calendar.add(Calendar.DATE, offset);      
	    return calendar.getTime();      
	} 
	
	/**
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static Date getMinute(Date date ,int offset) {      
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(date);    
	    calendar.add(Calendar.MINUTE, offset);  
	     
	    return calendar.getTime();      
	} 
	
	public static int getHourValue() {      
	    Calendar calendar = Calendar.getInstance();  
	    calendar.setTime(new Date());    
	     
	    return calendar.get(Calendar.HOUR_OF_DAY);   
	} 
	
	public static Date getFirstDay(String year, String month)    
    throws ParseException {    
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
		return format.parse(year + "-" + month + "-1");    
	}  
	
	
	public static String getDate(Date date,String format,int offset){
		if(StringUtils.isNotBlank(format)){
			Calendar scalendar = Calendar.getInstance();
			if(date==null){
				scalendar.setTime(new Date());
			}else{
				scalendar.setTime(date);
			}
			if(format.length()==6){
				scalendar.add(Calendar.MONTH, offset);  
			}else if(format.length()==8){
				scalendar.add(Calendar.DAY_OF_MONTH, offset);
			}else if(format.length()==7){
				if(format.indexOf("W")>0){
					scalendar.add(Calendar.WEEK_OF_MONTH,offset);
				}
			}
			Date d = scalendar.getTime();
			SimpleDateFormat fmt = new SimpleDateFormat(format);
			return fmt.format(d);
		}else{
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
			if(date==null){
				return fmt.format(new Date());
			}else{
				return fmt.format(date);
			}
		}
	}
	
	public static String getCurDate(String format){
		String result = "";
		SimpleDateFormat smt = new SimpleDateFormat(format);
		result = smt.format(new Date());
		return result;
	}
	
	public static String getCurDate(String format,Date date){
		String result = "";
		SimpleDateFormat smt = new SimpleDateFormat(format);
		result = smt.format(date);
		return result;
	}
	
	/**
	 * 计算两个日期间的间隔
	 * @param d1 
	 * @param d2
	 * @param type 0分,1时,2天
	 * @return
	 */
	public static int dateDiff(Date d1,Date d2,int type){
		int result = 0;
		if(d1==null){
			d1 = new Date();
		}
		if(d2==null){
			d2 = new Date();
		}
		long t1 = 0L;
		long t2 = 0L;
		if (d1 != null) {
			t1 = d1.getTime();
		}
		if (d2 != null) {
			t2 = d2.getTime();
		}
		// 因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理
		int hours = (int) ((t1 - t2) / 3600000);
		int minutes = (int) (((t1 - t2) / 1000 - hours * 3600) / 60);
		//int second = (int) ((t1 - t2) / 1000 - hours * 3600 - minutes * 60);
		if(type==0){
			result = (hours*60)+minutes;
		}else if(type==1){
			result = hours;
		}else if(type==2){
			result = hours/24;
		}
		return result;
	}
	
	public static long getbetweenDate(Date fdate,Date edate){
		
		long day = 0; 
		try { 
		day = (edate.getTime() - fdate.getTime()) / (24 * 60 * 60 * 1000); 
		} catch (Exception e) { 
			return 0; 
		} 
		return day; 
		
	}
	
	public static void main(String[] args)
	{
		System.out.println(getHourValue());
			//System.out.println(getYearDate(new Date(),1));

	}
}
