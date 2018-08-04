package com.ecp.core.constant;

import com.ecp.core.utils.PropertiesUtil;


public class APIConstants {

	public static final String JSON_CODE = "code";
	public static final String JSON_MESSAGE = "message";
	public static final String JSON_DATA = "data";
	public static final int CODE_SUCCESS = 200;
	public static final int CODE_FAILD = 400;
	public static final int CODE_SERVER_ERR = 500;
	public static final int CODE_PARAM_ERR = -1; 
	public static final int CODE_VERIFYCODE_ERR = -2;
	public static final int CODE_AUTH_FAILD = 0;
	public static final int SCORE_VALUE = 100; //积分分值（临时）
	
	public static final String MESSAGE_SUCCESS = "成功";
	public static final String MESSAGE_FAILD = "失败";
	public static final String MESSAGE_PARAM_ERR = "参数错误";
	public static final String MESSAGE_SERVER_ERR = "服务异常";
	public static final String MESSAGE_AUTH_FAILD = "会话失效";
	public static final String MESSAGE_VERIFYCODE_ERR = "验证码错误";
	public static final String MESSAGE_VERIFY_EMAIL = "邮箱";
	
	public static final String IS_DEBUG = PropertiesUtil.getProperty("ecp.sys.debug");//是否是调试模式
	public static final String RSA_KEY_PATH = PropertiesUtil.getProperty("ecp.rsa.path");
	
	
}
