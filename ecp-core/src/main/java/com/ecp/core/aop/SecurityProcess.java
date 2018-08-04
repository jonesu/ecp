package com.ecp.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.ecp.core.struts.BaseAction;
import com.ecp.core.utils.RSAUtil;


@Component
public class SecurityProcess {

	public Object berforeprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		BaseAction action = (BaseAction) jp.getTarget();
		String param = action.getParam();
		if(param != null && !"".equals(param.trim()) ){
			action.setParam(param.trim());
			if(!(param.startsWith("{") && param.endsWith("}"))){
				String decodePatam = RSAUtil.decryptByPrivateKey(param);
				action.setParam(decodePatam);
				action.setCipherResponse(true);
			}
		}
		return jp.proceed();
	}
	
	public Object afterprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		Object rvt = jp.proceed();
		BaseAction action = (BaseAction) jp.getTarget();

		return rvt;
	}

}
