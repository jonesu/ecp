package com.ecp.core.constant;


public class WebConstants {

	private WebConstants() {
	}
	public final static String CHARACTER_ENCODING_UTF_8 = "UTF-8";
	
	public final static String SESSION_ATTRIBUTE_USER = "USER";            // 用户对象
	public final static String SESSION_ATTRIBUTE_USERINFO = "USERINFO"; 
	public final static String SESSION_ATTRIBUTE_EXPECTURL = "ExpectUrl"; // 权限检查出错前的用户预期访问页面
	public final static String SESSION_ATTRIBUTE_INDEXPAGE = "IndexPage"; // 用户定制首页
	
	/* 默认的每页记录数 */
	public final static int DEFAULT_PAGESIZE = 20;
	/* 默认的取第一页 */
	public final static int DEFAULT_PAGENO = 1;
	//EXCEL最大导出行数
	public final static int DEFAULT_PAGESIZE_MAX_EXCEL = 65000;
	
	public final static String PROPERTY_PREFIX = "_";
	
	public final static String REQUEST_ATTRIBUTE_PK = "PK";
	
	
	public final static String PAGE_ATTRIBUTE_ISNEW = "ISNEW";
	public final static String PAGE_ATTRIBUTE_ISEDIT = "ISEDIT";
	
	
	public final static String STRING_TRUE = "TRUE";
	public final static String STRING_FALSE = "FALSE";
	
	public final static String OPRTYPE_ADD = "A";
	public final static String OPRTYPE_MODIFY = "M";
	public final static String OPRTYPE_DELETE = "D";
	
	public final static String ERROR_MESSAGE = "errorMessage";
	public final static String DEFAULT_MESSAGE = "defaultMessage";
	public final static String 	COMMA_SEPARATOR=",";

	
	

}
