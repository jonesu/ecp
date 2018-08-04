package com.ecp.core.struts.type;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.StrutsResultSupport;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.util.ValueStack;

public class SimpleJsonTextResult extends StrutsResultSupport {

	private static final long serialVersionUID = -398771270503875355L;

	private String pContentType = "application/json;charset=UTF-8";

	private Writer writer;

	protected void doExecute(String locationArg, ActionInvocation invocation)throws Exception {
		
		ValueStack stack = ServletActionContext.getContext().getValueStack();
		String jsonObject = stack.findValue("responseJsonObject").toString();
		String jsoncallback = stack.findValue("jsoncallback")==null?null:stack.findValue("jsoncallback").toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(getContentType());
		if(StringUtils.isNotEmpty(jsoncallback)){
			getWriter().write(jsoncallback + "(" + jsonObject + ")");
		}else{
			getWriter().write(jsonObject);
		}
		getWriter().flush();
	}

	public void setContentType(String aContentType) {
		pContentType = aContentType;
	}

	public String getContentType() {
		return pContentType;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	protected Writer getWriter() throws IOException {
		if (writer != null) {
			return writer;
		}
		return ServletActionContext.getResponse().getWriter();
	}
}
