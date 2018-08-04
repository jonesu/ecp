package com.ecp.core.struts.type;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;

import com.ecp.core.basic.FileItem;



public class MultiPartRequest {
	
	
	public static FileItem getFile(HttpServletRequest request, String fieldName) {
		FileItem fileItem = null;
		if(request instanceof MultiPartRequestWrapper){
			MultiPartRequestWrapper req = (MultiPartRequestWrapper) ServletActionContext.getRequest();
			File[] files = req.getFiles(fieldName);
			if(files != null && files.length > 0){
				File file = files[0];
				String fileName = req.getFileNames(fieldName)[0];
				String contentType = req.getContentTypes(fieldName)[0];
				if(contentType.indexOf("/") > -1){
					contentType = contentType.split("/")[1];
				}
				fileItem = new FileItem(file, fileName, contentType);
			}
		}
		return fileItem;
	}
	
	public static List<FileItem> getFiles(HttpServletRequest request, String fieldName) {
		List<FileItem> fileItems = null;
		if(request instanceof MultiPartRequestWrapper){
			MultiPartRequestWrapper req = (MultiPartRequestWrapper) ServletActionContext.getRequest();
			
			fileItems = new ArrayList<FileItem>();
			Enumeration<String> mu = req.getFileParameterNames();
			while(mu.hasMoreElements()) {
				String _fieldName = mu.nextElement();
				if(_fieldName.indexOf(fieldName) > -1) {
					File[] files = req.getFiles(_fieldName);
					String[] fileNames = req.getFileNames(_fieldName);
					String[] contentTypes = req.getContentTypes(_fieldName);
					for(int i=0; i< files.length; i++){
						String contentType = contentTypes[i];
						if(contentType.indexOf("/") > -1){
							contentType = contentType.split("/")[1];
						}
						fileItems.add(new FileItem(files[i], fileNames[i], contentType));
					}
				}
				
			}
		}
		return fileItems;
	}
	

}
