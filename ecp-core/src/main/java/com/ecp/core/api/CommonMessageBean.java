package com.ecp.core.api;



public class CommonMessageBean {
	
	private int code;
	
	private String message;
	
	private transient boolean ret;//业务处理结果
	
	public CommonMessageBean(int code,String message,boolean ret){
		this.code = code;
		this.message = message;
		this.ret = ret;
	}
	
	public CommonMessageBean(int code,boolean ret){
		this(code, APIMessageCode.getMessage(String.valueOf(code)), ret);
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isRet() {
		return ret;
	}
	public void setRet(boolean ret) {
		this.ret = ret;
	}

}
