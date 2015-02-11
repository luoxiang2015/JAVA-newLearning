package com.huasoft.ilearning.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain fc) throws IOException, ServletException {
		
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		
		String path=req.getServletPath();
		if(path.equals("/login.jsp")||path.equals("/employeeAction!login.action")||path.equals("/temp.jsp")){
			fc.doFilter(request, response);
			return;
		}
		Object obj=req.getSession().getAttribute("curUser");
		if(obj!=null){
			
			fc.doFilter(request, response);
		}else{
			res.sendRedirect("http://localhost:8080/newLearning/temp.jsp");
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
