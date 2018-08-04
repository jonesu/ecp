package com.ecp.core.exception;

import com.ecp.core.api.APIMessageCode;
import com.ecp.core.api.CommonMessageBean;
import com.ecp.core.constant.APIConstants;


public class APIException extends RuntimeException{


	private static final long serialVersionUID = 11773303336881L;

	private int code;
	
	private String message;
	
	public APIException(int code){
		this(code, APIMessageCode.getMessage(code));
	}
	
	public APIException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public APIException(String message){
		this(APIConstants.CODE_SERVER_ERR, message);
	}
	
	public APIException(CommonMessageBean msg){
		super();
		this.code = msg.getCode();
		this.message = msg.getMessage();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
