package com.ecp.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 图片地址处理
 * @author liaojinhua
 *
 */
public class PicUrlUtils {
	public final static String PIC_PARAMS = "${pic_url}";
	public final static String PIC_PARAMS2 = "\\$\\{pic_url\\}";
	
	//${pic_url} to http://
	public static String replaceUrl(String url){
		try{
			if(url == null)return url;
			String value = PropertiesUtil.getProperty("img.server.url");
			if(value == null)return url;
			//url = url.replaceAll("\\"+PIC_PARAMS,value);
			String re1  = "\\$\\{pic_url\\}"; 
			Pattern p1 = Pattern.compile(re1); 
			Matcher m1 = p1.matcher(url); 
			while(m1.find()){    
			    String tmp = m1.group(); 
			    String key = tmp.substring(2,tmp.length()-1); 
			    
			    url = url.replaceAll("\\$\\{pic_url\\}", value);
			    url = url.replace("\\","/");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
	}
	
	public static String  replaceUrlToPath(String url){
		try{
			if(url == null)return url;
			String value = PropertiesUtil.getProperty("img.file.root");
			//String value = "C:/work/server/apache-tomcat-7.0.53/webapps/imgserver/images";
			//url = url.replaceAll("\\"+PIC_PARAMS,value);
			String re1  = "\\$\\{pic_url\\}"; 
			Pattern p1 = Pattern.compile(re1); 
			Matcher m1 = p1.matcher(url); 
			while(m1.find()){    
			    String tmp = m1.group(); 
			    String key = tmp.substring(2,tmp.length()-1); 
			    
			    url = url.replaceAll("\\$\\{pic_url\\}", "");
			    
			}
			url = value+url;
			  
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
	}
	
	public static String  replaceUrlToEmpt(String url){
		try{
			if(url == null)return url;
			String value = "";
			//url = url.replaceAll("\\"+PIC_PARAMS,value);
			String re1  = "\\$\\{pic_url\\}"; 
			Pattern p1 = Pattern.compile(re1); 
			Matcher m1 = p1.matcher(url); 
			while(m1.find()){    
			    String tmp = m1.group(); 
			    String key = tmp.substring(2,tmp.length()-1); 
			    
			    url = url.replaceAll("\\$\\{pic_url\\}", value);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
	}
	//http to params
	public static String replaceUrlToParams(String url){
		try{
			if(url == null)return url;
			String value = PropertiesUtil.getProperty("img.server.url");
			url = url.replaceAll(value, PIC_PARAMS2);
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
	}
	
	/**
	 * 替换用户头像地址
	 * @param url
	 * @return
	 */
	public static String replaceHeadUrl(String url){
		try{
			String value = PropertiesUtil.getProperty("img.server.url");
			if(value == null)return url;
			if(url == null || url.equals("")) {
				url = value + "/img/head.png";//默认头像
				return url;
			}
			
			String re1  = "\\$\\{pic_url\\}"; 
			Pattern p1 = Pattern.compile(re1); 
			Matcher m1 = p1.matcher(url); 
			while(m1.find()){    
			    String tmp = m1.group(); 
			    String key = tmp.substring(2,tmp.length()-1); 
			    
			    url = url.replaceAll("\\$\\{pic_url\\}", value);
			    url = url.replace("\\","/");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return url;
	}
	
	
	public static void main(String[] args){
		//http://localhost:9081/gcgh/images/tuan//团购/团购1/cMBaQOlmjsgNIkX.jpeg
		//System.out.println(PicUrlUtils.replaceUrl("${pic_url}\\items\\1972\\main.png"));
		System.out.println(PicUrlUtils.replaceUrlToParams("${pic_url}\\items\\1972\\main.png"));
	}
}
