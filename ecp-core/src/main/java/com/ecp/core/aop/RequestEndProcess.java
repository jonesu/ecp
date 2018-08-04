package com.ecp.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.ecp.core.constant.APIConstants;
import com.ecp.core.exception.APIException;
import com.ecp.core.struts.BaseAction;


@Component
public class RequestEndProcess {

	public Object endprocess(ProceedingJoinPoint jp) {
		Throwable exp  = null;
		try {
			jp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
			exp = e;
		}
		exceptionProcess(jp, exp);
		return "success";
	}

	private void exceptionProcess(ProceedingJoinPoint jp, Throwable exp) {
		if(exp != null){
			BaseAction action = (BaseAction) jp.getTarget();
			if(exp instanceof APIException){
				APIException e = (APIException) exp;
				action.setResponseJsonObject(action.getSimpleJsonTextResponse(e.getCode(), e.getMessage()));
			}else{
				action.setResponseJsonObject(action.getSimpleJsonTextResponse(APIConstants.CODE_SERVER_ERR, APIConstants.MESSAGE_SERVER_ERR));
			}
		}
	}
	
}
