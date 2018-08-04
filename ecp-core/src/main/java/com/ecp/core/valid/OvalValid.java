package com.ecp.core.valid;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import com.ecp.core.anno.FieldNameAnnotation;
import com.ecp.core.constant.APIConstants;
import com.ecp.core.exception.APIException;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.localization.locale.ThreadLocalLocaleProvider;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;






/**
 * 
Assert=不满足如下条件：{expression}
AssertFalse=的值不为false
AssertNull= must be null
AssertTrue=的值不为true
AssertURL= is not a valid URL
CheckWith= {simpleCheck} 不满足
DateRange= is not in the range {min} through {max}
Email= is not a valid e-mail address
EqualToField= is not equal to field {fieldName}
Future=必须在future中
HasSubstring=必须包含子串'{substring}'
InstanceOf=必须是一个{types}类型的实例
InstanceOfAny=必须是一个{types}类型的实例
Length=的长度不在{min}到{max}个字符之间
MatchPattern=不匹配模式{pattern}
Max=不能超过{max}
MaxLength=不能超过{max}个字符
MaxSize=不能包含超过{max}个元素
MemberOf= must be one of these values: {members}
Min=不能比{min}小
MinLength=不能少于{min}个字符
MinSize=不能包含少于{min}个元素
NoSelfReference=非法的自我引用
NotBlank=不能是空白(blank)的
NotEmpty=不能是空(empty)的
NotEqual= must not equal {testString}
NotEqualToField= must not equal to field {fieldName}
NotMatchPattern= must not match the pattern {pattern}
NotMemberOf= must not be one of these values: {members}
NotNegative=不能是负的
NotNull=不能是空(null)的
Past=必须在past中
Range=不在从{min}到{max}的范围里
Size=不包含介于{min}个到{max}个之间的元素
ValidateWithMethod=方法{methodName}({parameterType})决定是不合法的
 */

public class OvalValid implements Valid {

	private static Map<String,String> fieldNameMap = new HashMap<String,String>();
	
	static {
		ResourceBundleMessageResolver.INSTANCE.addMessageBundle(ResourceBundle.getBundle("com/ecp/commons/valid/Messages", Locale.CHINA));
        ThreadLocalLocaleProvider p = new ThreadLocalLocaleProvider();
        p.setLocale(Locale.CHINA);
	}
	
	public void valid(Object obj) {
		Validator validator = new Validator(); 
		//System.out.println(obj.getClass());
        List<ConstraintViolation> ret = validator.validate(obj);
        if(ret != null && ret.size() > 0){
        	StringBuilder builder = new StringBuilder();
        	for(ConstraintViolation v : ret){
        		String fieldName = v.getContext().toString();
        		String defineFieldName = fieldNameMap.get(fieldName);
        		if(defineFieldName == null){
        			initFieldName(obj);
        			defineFieldName = fieldNameMap.get(fieldName);
        		}
        		builder.append(defineFieldName==null?"参数":defineFieldName);
        		builder.append(v.getMessage());
        		break;
        	}
        	throw new APIException(APIConstants.CODE_PARAM_ERR,builder.toString());
        }
	}
	
	public void initFieldName(Object obj){
		Field[] field = obj.getClass().getDeclaredFields();
    	for(Field f : field){

    		for(Annotation ano : f.getAnnotations()){
    			if(ano instanceof FieldNameAnnotation){
    				fieldNameMap.put(f.getDeclaringClass().getName() + "." + f.getName(), ((FieldNameAnnotation)ano).value());
    			}
    		}
    	}
	}

}
