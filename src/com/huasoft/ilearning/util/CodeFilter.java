package com.huasoft.ilearning.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CodeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest res, ServletResponse resp,
			FilterChain fc) throws IOException, ServletException {
		// TODO Auto-generated method stub
		res.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		fc.doFilter(res, resp);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
