package com.ecp.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

/**
 * Properties工具类
 * 使用方法，如:<br/>
 * 默认装载方式: PropertiesUtil.loadProperties("conf")<br/>
 * 重新装载方式: PropertiesUtil.loadProperties("conf",true)
 */
public class PropertiesUtil {
	
	private static Hashtable<String,Properties> pros = new Hashtable<String,Properties>();
	private static final String FILE_NAME = "config";
	
	/**
	 * 装载Properties文件(如果缓存中已存在，则不加载)
	 * @param name Properties文件名(不包括扩展名)
	 * @return Properties对象
	 */
	private static Properties loadProperties(String name) {
		return loadProperties(name, false);
	}
	
	/**
	 * 装载Properties文件
	 * @param name Properties文件名(不包括扩展名)
	 * @param isReload 是否重新加载
	 * @return Properties对象
	 */
	private static Properties loadProperties(String name, Boolean isReload) {
		if (pros.contains(name)&&!isReload)
			return pros.get(name);		
		else 
			return doLoadProperties(name);
	}
	
	/**
	 * 装载Properties文件--具体实现
	 * @param name Properties文件名(不包括扩展名)
	 * @return Properties对象
	 */
	private static Properties doLoadProperties(String name) {
		Properties pro = new Properties();
		try {
			InputStream inStream = PropertiesUtil.class.getResourceAsStream("/"+name+".properties");
			if(inStream!=null) {
				pro.load(inStream);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		pros.put(name, pro);
		return pro;
	}
	
	public static String getProperty(String key){
		Properties p = loadProperties(FILE_NAME);
		if(key == null || p == null){
			return "";
		}
		return p.getProperty(key);
	}
	public static Long getLongProperty(String key){
		Properties p = loadProperties(FILE_NAME);
		if(key == null || p == null){
			return null;
		}
		String value = p.getProperty(key);
		if(value != null && !value.equals("")){
			return Long.valueOf(value);
		}else{
			return null;
		}
	}
}
