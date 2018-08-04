package com.ecp.core.api;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class APIMessageCode {
	
	private static Map<String, String> messageCode = new HashMap<String,String>();
	
	public static Map<String, String> getMessageCode() {
		return messageCode;
	}

	public static void setMessageCode(Map<String, String> map) {
		if(map != null){
			for(Entry<String,String> entry : map.entrySet()){
				messageCode.put(entry.getKey(), entry.getValue());
			}
		}
	}
	
	public static String getMessage(String code){
		return messageCode.get(code);
	}
	
	public static String getMessage(int code){
		return messageCode.get(String.valueOf(code));
	}
}
