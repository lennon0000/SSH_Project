package org.leadfar.egov.servlet;

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

	public void destroy() {

	}

	private String[] checkUrls = { "Servlet", "html", "htm", "jsp","index" };

	public void doFilter(ServletRequest req, ServletResponse resp,

	FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (this.check(request.getRequestURI())) {
			if (request.getSession().getAttribute("LOGIN_USER") == null) {// 不能直接这样进行判断，否则进入死循环，前边得设定条件
				response.sendRedirect(request.getContextPath() + "/");
				return ;
			}
		}

		chain.doFilter(req, resp);
		//System.out.println("after");

	}

	private boolean check(String url) {
		for (String urls : checkUrls) {
			if(url.endsWith(urls)){
				return true;
			}
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
