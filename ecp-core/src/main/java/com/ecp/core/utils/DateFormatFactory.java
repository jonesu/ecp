package com.ecp.core.utils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;



public class DateFormatFactory {
	
	static Map<String, SimpleDateFormat> dateFormatCache = new HashMap<String, SimpleDateFormat>();
	
	public static final String defaultDateFormatPattern = "yyyy-MM-dd HH:mm:ss";
	
	public static final String shortMonthDateFormatPattern = "yyyyMM";
	
	public static final String shortDateFormatPattern = "yyyy-MM-dd";
	
	public static final String appDatetimeFormatPattern = "yyyy/MM/dd HH:mm:ss";
	
	public static SimpleDateFormat getInstance(String pattern){
		if(StringUtils.isNotBlank(pattern)){
			if(dateFormatCache.containsKey(pattern)){
				return dateFormatCache.get(pattern);
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormatCache.put(pattern, dateFormat);
			return dateFormat;
		}
		return getDefaultDateFormat();
	}
	
	/**
	 * 轮换默认日期格式字符串
	 * 
	 * 月份格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * 举例：2012-07-01 00:00:00
	 * 
	 * @return
	 */
	public static SimpleDateFormat getDefaultDateFormat(){
		return DateFormatFactory.getInstance(defaultDateFormatPattern);
	}
	
	/**
	 * 轮换月份字符串
	 * 
	 * 月份格式：yyyyMM
	 * 
	 * 举例：201207
	 * 
	 * @return
	 */
	public static SimpleDateFormat getShortMonthDateFormat(){
		return DateFormatFactory.getInstance(shortMonthDateFormatPattern);
	}
	
	/**
	 * 轮换日期格式字符串
	 * 
	 * 月份格式：yyyy-MM-dd
	 * 
	 * 举例：2012-07-01
	 * 
	 * @return
	 */
	public static SimpleDateFormat getShortDateDateFormat(){
		return DateFormatFactory.getInstance(shortDateFormatPattern);
	}
	
	public static SimpleDateFormat getAppDatetimeFormatPattern(){
		return DateFormatFactory.getInstance(appDatetimeFormatPattern);
	}

}
