package com.ecp.core.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.ecp.core.anno.JsonActionAnnotation;
import com.ecp.core.api.CommonMessageBean;
import com.ecp.core.api.CommonReqBean;
import com.ecp.core.api.MCacheUtil;
import com.ecp.core.exception.APIException;
import com.ecp.core.struts.BaseAction;
import com.ecp.core.utils.GsonBuilder;



@Component
public class JsonRequestActionProcess {
	
	public static Map<String,JsonActionAnnotation> annomap = new HashMap<String,JsonActionAnnotation>();

	public Object berforeprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		BaseAction action = (BaseAction) jp.getTarget();
		Method methodsrc = ((MethodSignature)jp.getSignature()).getMethod();
		
		String jpmname = jp.getTarget().getClass().getSimpleName() + "." + methodsrc.getName();
		if(annomap.get(jpmname) == null){
			Method[] method = jp.getTarget().getClass().getDeclaredMethods();
			loop:for(Method m : method){
				if(m.equals(methodsrc)){
					for(Annotation ano : m.getAnnotations()){
						 if(ano.annotationType().equals(JsonActionAnnotation.class)){
							 annomap.put(jpmname, (JsonActionAnnotation) ano);
							 break loop;
						 }
					}
				}
			}
		}
		
		JsonActionAnnotation jsonano = annomap.get(jpmname);
		if(jsonano != null){
			Object obj = GsonBuilder.getGson().fromJson(action.getParam(), jsonano.inputProcClass());
			action.setParamObject(obj);
			if(obj instanceof CommonReqBean){
				CommonReqBean req = (CommonReqBean)obj;
				action.setTokenid(req.getTokenid());
				req.setJpmname(jpmname);
				req.setJsonano(jsonano);
			}
			if (jsonano.session()) {
				CommonMessageBean commonMessageBean = MCacheUtil.validateSession(action.getTokenid());
				if (!commonMessageBean.isRet()) {
					throw new APIException(commonMessageBean);
				}
			}
		}
		
		return jp.proceed();
	}
	
	public Object afterprocess(ProceedingJoinPoint jp) throws java.lang.Throwable {
		Object rvt = jp.proceed();
		BaseAction action = (BaseAction) jp.getTarget();
		action.setResponseObject(rvt);
		action.setResponseJsonObject(GsonBuilder.getGson().toJson(rvt));
		return rvt;
	}
	
}
