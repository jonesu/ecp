package com.ecp.core.utils;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;


public class GsonBuilder {
	
	private static Gson gson = new com.google.gson.GsonBuilder()
		//.excludeFieldsWithoutExposeAnnotation()
		.serializeNulls()//该设置如果字段为空，则返回空值
		.disableHtmlEscaping()
		.registerTypeAdapter(java.util.Date.class, new SelfJsonSerializer<java.util.Date>())
		.registerTypeAdapter(java.sql.Date.class, new SelfJsonSerializer<java.sql.Date>())
		.registerTypeAdapter(java.sql.Timestamp.class, new SelfJsonSerializer<java.sql.Timestamp>())
		.registerTypeAdapter(java.lang.Long.class, new SelfJsonSerializer<java.lang.Long>())
		.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		//.setDateFormat(DateFormat.LONG).create();
	
	
	public static Gson getGson(){
		return gson;
	}
	
	public static String bean2Json(Object bean){
		return gson.toJson(bean);
	}
	
	public static String list2Json(List<?> list){
		return gson.toJson(list);
	}
	
	public static String map2Json(Map<?, ?> map){
		return gson.toJson(map);
	}
	

	public static void main(String []args){
	}

}
