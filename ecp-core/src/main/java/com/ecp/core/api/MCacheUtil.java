package com.ecp.core.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.ecp.core.basic.StringHelper;
import com.ecp.core.constant.APIConstants;
import com.ecp.core.utils.MemcachedUtil;


public class MCacheUtil {

	private static IMemcachedCache sessionCache = MemcachedUtil.getMemcahcedClientInstance("sessionCache");
	private static IMemcachedCache verifyCodeCache = MemcachedUtil.getMemcahcedClientInstance("verifyCodeCache");
	private static IMemcachedCache baseIfnfoCache = MemcachedUtil.getMemcahcedClientInstance("baseInfoCache");


	
	private static String CLIENT_PUBBLICKEY_CACHEKEY_PRE = "PK_";//公钥在Cache中的key是PK_${tokenid},不存在超时的情况
	private static String ACCOUNT_CACHEKEY_PRE = "AC_";//帐号在Cache中的key是PK_${tokenid}
	private static String PASSID_CACHEKEY_PRE = "PA_";//帐号在Cache中的key是PK_${tokenid}
	
	
	private static String PRODUCT_ID_CACHEKEY_PRE = "BI_RS_PRODUCT_ID_";
	private static String PRODUCT_CODE_CACHEKEY_PRE = "BI_RS_PRODUCT_CODE_";
	private static String PRODUCT_LIVE_PRICE_KEY = "PRODUCT_LIVE_PRICE";


	
	public static String getClientPublicKey(String tokenid){
		String clientPublicKey = StringHelper.objToStr(sessionCache.get(CLIENT_PUBBLICKEY_CACHEKEY_PRE+tokenid));
		return clientPublicKey;
	}
	
	public static String setClientPublicKey(String tokenid,String clientPublicKey){
		sessionCache.put(CLIENT_PUBBLICKEY_CACHEKEY_PRE + tokenid,clientPublicKey);
		return clientPublicKey;
	}
	
	public static String getAccount(String tokenid){
		String account = StringHelper.objToStr(sessionCache.get(ACCOUNT_CACHEKEY_PRE+tokenid));
		return account;
	}
	
	public static String setAccount(String tokenid,String account){
		sessionCache.put(ACCOUNT_CACHEKEY_PRE + tokenid,account);
		return account;
	}
	
	public static String setVerifyCode(String verifyCodeKey,String verifyCode){
		IMemcachedCache cache = MemcachedUtil.getMemcahcedClientInstance("verifyCodeCache");
		cache.put(verifyCodeKey, verifyCode, new Date(20 * 60 * 1000));//20分钟过期,冉写在配置上面
		return verifyCode;
	}
	/**
	 * 微信注册手机号验证码
	 * @return
	 */
	public static String setWeiXinVerifyCode(String verifyCodeKey,String verifyCode){
		IMemcachedCache cache = MemcachedUtil.getMemcahcedClientInstance("verifyCodeCache");
		cache.put(verifyCodeKey, verifyCode, new Date(5 * 60 * 1000));//5分钟过期,冉写在配置上面
		return verifyCode;
	}
	/**
	 * 校验微信绑定手机短信验证码
	 * @return
	 */
	public static boolean validateWeixinVerifyCode(String verifyCodeKey,String verifyCode){
		Object codeCache = new Object(); 
		try{
			codeCache = verifyCodeCache.get(verifyCodeKey);
		}catch(Exception e){
			return false;
		}
		String cacheVerifyCode = StringHelper.objToStr(codeCache);
		System.out.println("cacheVerifyCode:"+cacheVerifyCode);
		if(!StringUtils.isEmpty(verifyCode) && !StringUtils.isEmpty(verifyCode)){
			if(cacheVerifyCode.equals(verifyCode)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 校验验证码
	 * @return
	 */
	public static CommonMessageBean validateVerifyCode(String verifyCodeKey,String verifyCode){
		if(APIConstants.IS_DEBUG.trim().equals("true")){
			return new CommonMessageBean(APIConstants.CODE_SUCCESS, APIConstants.MESSAGE_SUCCESS, true);
		}
		String cacheVerifyCode = StringHelper.objToStr(verifyCodeCache.get(verifyCodeKey));
		if(!StringUtils.isEmpty(verifyCode) && !StringUtils.isEmpty(verifyCode)){
			if(!cacheVerifyCode.equals(verifyCode)){
				return new CommonMessageBean(APIConstants.CODE_VERIFYCODE_ERR, APIConstants.MESSAGE_VERIFYCODE_ERR, false);
			}
		}
		return new CommonMessageBean(APIConstants.CODE_SUCCESS, APIConstants.MESSAGE_SUCCESS, true);
	}
	
	/**
	 * 检测登陆后的会话
	 * @return
	 */
	public static CommonMessageBean validateSession(String tokenid){
		if(StringUtils.isEmpty(tokenid)){
			return new CommonMessageBean(APIConstants.CODE_AUTH_FAILD,APIConstants.MESSAGE_AUTH_FAILD,false);
		}
		Long passId = getPassId(tokenid);
		if(passId == null) {
			return new CommonMessageBean(APIConstants.CODE_AUTH_FAILD,APIConstants.MESSAGE_AUTH_FAILD,false);
		}

		return new CommonMessageBean(APIConstants.CODE_SUCCESS,APIConstants.MESSAGE_SUCCESS,true);
	}
	
	public static boolean setPassId(String tokenid,Long passId){
		sessionCache.put(PASSID_CACHEKEY_PRE + tokenid,passId);
		return true;
	}
	
	public static Long getPassId(String tokenid){
		if(StringUtils.isEmpty(tokenid)){
			return null;
		}
		String str = StringHelper.objToStr(sessionCache.get(PASSID_CACHEKEY_PRE + tokenid));
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return Long.valueOf(str);
	}
	
	public static void setProductLivePrice(List<Map<String,Object>> list){
		baseIfnfoCache.put(PRODUCT_LIVE_PRICE_KEY, list);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Map<String,Object>> getProductLivePrice(){
		Object obj = baseIfnfoCache.get(PRODUCT_LIVE_PRICE_KEY);
		return obj == null ? null : (List<Map<String,Object>>)obj;
	}
	
}
