package com.ecp.core.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonActionAnnotation {

	String value() default "";
	
	Class<?> inputProcClass() default Object.class;
	
	boolean session() default false; //app 使用验证当前是否登陆
	
	String verifypasslevel() default "1"; //web 服务验证 pass 是否有效的注解; 1: 必须有效; 2:必须存在不必有效; 3:忽略
	
	String loggername() default "default"; //记录到日志文件
	
	boolean dblogger() default true; //记录到数据库
}
