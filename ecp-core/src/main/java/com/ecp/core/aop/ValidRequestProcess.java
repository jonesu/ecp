package com.ecp.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.ecp.core.api.CommonReqBean;
import com.ecp.core.constant.APIConstants;
import com.ecp.core.exception.APIException;
import com.ecp.core.struts.BaseAction;
import com.ecp.core.valid.OvalValid;


@Component
public class ValidRequestProcess {

	public Object berforeprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		BaseAction action = (BaseAction) jp.getTarget();
		Object jsonObject = action.getParamObject();

		if(jsonObject != null){
			new OvalValid().valid(jsonObject);
		}
		return jp.proceed();
	}
	
	public Object berforewebprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		BaseAction action = (BaseAction) jp.getTarget();
		Object jsonObject = action.getParamObject();
		if(jsonObject instanceof CommonReqBean){
			CommonReqBean commonReqBean = (CommonReqBean) jsonObject;
			if (("1".equals(commonReqBean.getJsonano().verifypasslevel()) || "2".equals(commonReqBean.getJsonano().verifypasslevel())) && org.apache.commons.lang3.StringUtils.isEmpty(commonReqBean.getAccount())) {
				throw new APIException(APIConstants.CODE_PARAM_ERR, "通行证帐号不能为空");
			}
		}
		if(jsonObject != null){
			new OvalValid().valid(jsonObject);
		}
		return jp.proceed();
	}
	
	public Object berforesmsprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		BaseAction action = (BaseAction) jp.getTarget();
		Object jsonObject = action.getParamObject();
		if(jsonObject != null){
			new OvalValid().valid(jsonObject);
		}
		return jp.proceed();
	}
	
	public Object berforepayprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		BaseAction action = (BaseAction) jp.getTarget();
		Object jsonObject = action.getParamObject();
		if(jsonObject != null){
			new OvalValid().valid(jsonObject);
		}
		return jp.proceed();
	}
}
