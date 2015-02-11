package com.huasoft.ilearning.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

public class BaseAction {
	
	protected PrintWriter getOuter() throws IOException{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		return response.getWriter();
		
	}
	

}
