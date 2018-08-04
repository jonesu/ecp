package com.ecp.core.struts;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import com.ecp.core.api.CommonMessageBean;
import com.ecp.core.api.MCacheUtil;
import com.ecp.core.constant.APIConstants;
import com.ecp.core.utils.GsonBuilder;



public abstract class BaseAction {

	private static final Logger log = Logger.getLogger(BaseAction.class);

	private Object responseObject; //响应时返回的对象

	private Object responseJsonObject; //响应的 json

	private String param; //请求的入参
	
	private Object paramObject; //请求参数封装
	
	private String jsoncallback;
	
	private boolean cipherResponse; //是否密文返回
	
	private String tokenid;

	public Map<String, Object> jsonMap = null;

	public BaseAction() {
		jsonMap = new HashMap<String, Object>();
	}
	
	public Long getPassId(){
		if(!StringUtils.isEmpty(this.tokenid)){
			return MCacheUtil.getPassId(tokenid);
		}
		return null;
	}
	
	public String getAccount(){
		if(!StringUtils.isEmpty(this.tokenid)){
			return MCacheUtil.getAccount(tokenid);
		}
		return null;
	}

	public Map<String,Object> initJsonMap(){
		return initJsonMap(APIConstants.CODE_SUCCESS);
	}
	
	public Map<String,Object> initJsonMap(boolean success){
		if(success){
			initJsonMap(APIConstants.CODE_SUCCESS);
		}else{
			initJsonMap(APIConstants.CODE_FAILD);
		}
		return jsonMap;
	}
	
	public Map<String,Object> initJsonMap(int code){
		if(code == APIConstants.CODE_SUCCESS){
			jsonMap.put(APIConstants.JSON_CODE, APIConstants.CODE_SUCCESS);
			jsonMap.put(APIConstants.JSON_MESSAGE, APIConstants.MESSAGE_SUCCESS);
		}else if(code == APIConstants.CODE_FAILD){
			jsonMap.put(APIConstants.JSON_CODE, APIConstants.CODE_FAILD);
			jsonMap.put(APIConstants.JSON_MESSAGE, APIConstants.MESSAGE_FAILD);
		}else if(code == APIConstants.CODE_PARAM_ERR){
			jsonMap.put(APIConstants.JSON_CODE, APIConstants.CODE_PARAM_ERR);
			jsonMap.put(APIConstants.JSON_MESSAGE, APIConstants.MESSAGE_PARAM_ERR);
		}else if(code == APIConstants.CODE_SERVER_ERR){
			jsonMap.put(APIConstants.JSON_CODE, APIConstants.CODE_SERVER_ERR);
			jsonMap.put(APIConstants.JSON_MESSAGE, APIConstants.MESSAGE_SERVER_ERR);
		}else if(code == APIConstants.CODE_AUTH_FAILD){
			jsonMap.put(APIConstants.JSON_CODE, APIConstants.CODE_AUTH_FAILD);
			jsonMap.put(APIConstants.JSON_MESSAGE, APIConstants.MESSAGE_AUTH_FAILD);
		}else if(code == APIConstants.CODE_VERIFYCODE_ERR){
			jsonMap.put(APIConstants.JSON_CODE, APIConstants.CODE_VERIFYCODE_ERR);
			jsonMap.put(APIConstants.JSON_MESSAGE, APIConstants.MESSAGE_VERIFYCODE_ERR);
		}
		return jsonMap;
	}
	public Map<String,Object> initJsonMap(CommonMessageBean msg){
		jsonMap.put(APIConstants.JSON_CODE, msg.getCode());
		jsonMap.put(APIConstants.JSON_MESSAGE, msg.getMessage());
		return jsonMap;
	}
	
	public String getSimpleJsonTextResponse(int code, String message){
		StringBuilder builder = new StringBuilder();
		builder.append("{\"");
		builder.append(APIConstants.JSON_CODE);
		builder.append("\":");
		builder.append(code);
		builder.append(",\"");
		builder.append(APIConstants.JSON_MESSAGE);
		builder.append("\":\"");
		builder.append(message);
		builder.append("\"}");
		return builder.toString();
	}

	public Object createJsonObject(Class jsonclass){
		Object json = null;
		try {
			if (param != null && !"".equals(param)) {
				json = GsonBuilder.getGson().fromJson(param, jsonclass);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public HttpServletRequest getHttpServletRequest(){
		HttpServletRequest request = null;
		try{
			request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return request;
	}
	
	public HttpServletResponse getHttpServletResponse() {
		HttpServletResponse response = null;
		try{
			response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
    }
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public boolean isCipherResponse() {
		return cipherResponse;
	}

	public void setCipherResponse(boolean cipherResponse) {
		this.cipherResponse = cipherResponse;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public String getJsoncallback() {
		return jsoncallback;
	}

	public void setJsoncallback(String jsoncallback) {
		this.jsoncallback = jsoncallback;
	}

	public Object getResponseJsonObject() {
		return responseJsonObject;
	}

	public void setResponseJsonObject(Object responseJsonObject) {
		this.responseJsonObject = responseJsonObject;
	}

	public Object getParamObject() {
		return paramObject;
	}

	public void setParamObject(Object paramObject) {
		this.paramObject = paramObject;
	}
	
	public Object getJsonObect() {
		return paramObject;
	}

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}

}