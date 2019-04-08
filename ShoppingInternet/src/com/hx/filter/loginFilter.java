package com.hx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginFilter implements Filter {

	private String username;
	private String redirect;
	
    public loginFilter() {
    }

	public void destroy() {
	}

	public void init(FilterConfig fConfig) throws ServletException {
		username=fConfig.getInitParameter("name");
		redirect=fConfig.getInitParameter("redirect");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)arg0;
		HttpServletResponse response=(HttpServletResponse)arg1;
		String user=(String) request.getSession().getAttribute(username);
		if(user==null) {
			response.sendRedirect(request.getContextPath()+"/"+redirect);
		}
		chain.doFilter(request, response);
	}

}
