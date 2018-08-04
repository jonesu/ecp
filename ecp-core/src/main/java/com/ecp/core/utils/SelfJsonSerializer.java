package com.ecp.core.utils;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class SelfJsonSerializer<T> implements JsonSerializer<T>{

	public JsonElement serialize(T src, Type typeOfSrc, JsonSerializationContext context) {
		if(src instanceof java.util.Date){
			return new JsonPrimitive(DateFormatFactory.getDefaultDateFormat().format((java.util.Date)src));
		}else if(src instanceof java.sql.Date){
			return new JsonPrimitive(DateFormatFactory.getShortDateDateFormat().format((java.sql.Date)src));
		}else{
			return new JsonPrimitive(String.valueOf(src));
		}
		
	}

}
