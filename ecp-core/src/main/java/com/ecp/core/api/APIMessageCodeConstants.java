package com.ecp.core.api;

public class APIMessageCodeConstants {
	
	/**
	 * 成功
	 */
	public static int CODE_SUCCESS = 200;
	
	/**
	 * 服务异常
	 */
	public static int CODE_SERVER_ERR = 500;
	
	/**
	 * 失败
	 */
	public static int CODE_FAILD = 400;
	
	/**
	 * 参数错误
	 */
	public static int CODE_PARAM_ERR = -1;
	
	/**
	 * 账号不可用
	 */
	public static int CODE_ACCOUNT_UNAVAILABLE = -101;
	
	/**
	 * 账户错误
	 */
	public static int CODE_ACCOUNT_ERR = -102;
	
	/**
	 * 账号已存在
	 */
	public static int CODE_ACCOUNT_EXISTERR = -103;
	
	/**
	 * 账号不存在
	 */
	public static int CODE_ACCOUNT_NOTEXISTERR = -104;
	
	/**
	 * 帐号已激活
	 */
	public static int CODE_ACCOUNT_ACTIVITE = -105;
	
	/**
	 * 账号激活已失效
	 */
	public static int CODE_ACCOUNT_DISACTIVITE = -106;
	
	/**
	 * 账号未激活
	 */
	public static int CODE_ACCOUNT_NOTACTIVITE = -107;
	
	/**
	 * 消费者会员信息不存在
	 */
	public static int CODE_CONSUMER_ACCOUNT_NOTEXIST = -108;
	
	/**
	 * 代理商信息不存在
	 */
	public static int CODE_CONSIGNMENT_ACCOUNT_NOTEXIST = -109;
	
	/**
	 * 订单号不存在
	 */
	public static int CODE_ORDER_NOTEXISTERR = -201;
	
	/**
	 * 订单已支付
	 */
	public static int CODE_ORDER_PAYED = -202;
	
	/**
	 * 支付单不存在
	 */
	public static int CODE_PAYORDER_NOTEXISTERR = -203;
	
}
