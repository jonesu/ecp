package com.ecp.core.api;

import com.ecp.core.anno.JsonActionAnnotation;
import com.ecp.core.constant.WebConstants;




public class CommonReqBean {

	private int fromChannel;
	
	private String tokenid;
	
	private String verifyCodeKey;
	
	private String verifyCode;

	private String account;

	private int pageSize;
	
	private int pageNo;
	
	private JsonActionAnnotation jsonano;
	
	private String jpmname;
	
	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public int getFromChannel() {
		return fromChannel;
	}

	public void setFromChannel(int fromChannel) {
		this.fromChannel = fromChannel;
	}

	public String getTokenid() {
		return tokenid;
	}

	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}

	public String getVerifyCodeKey() {
		return verifyCodeKey;
	}

	public void setVerifyCodeKey(String verifyCodeKey) {
		this.verifyCodeKey = verifyCodeKey;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getPageSize() {
		if(this.pageSize == 0){
			this.pageSize = WebConstants.DEFAULT_PAGESIZE;
		}
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		if(this.pageNo == 0){
			this.pageNo = 1;
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public JsonActionAnnotation getJsonano() {
		return jsonano;
	}

	public void setJsonano(JsonActionAnnotation jsonano) {
		this.jsonano = jsonano;
	}

	public String getJpmname() {
		return jpmname;
	}

	public void setJpmname(String jpmname) {
		this.jpmname = jpmname;
	}
}
