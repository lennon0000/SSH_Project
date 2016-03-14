package cn.com.leadfar.oa.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import cn.com.leadfar.oa.utils.SystemContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PagerInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		SystemContext.setOffset(getRequestOffset(ServletActionContext.getRequest()));
		SystemContext.setPagesize(getRequestPagesize(ServletActionContext.getRequest()));
		
		try{
			return invocation.invoke();
		}finally{
			SystemContext.removeOffset();
			SystemContext.removePagesize();
		}
	}
	
	private int getRequestOffset(HttpServletRequest request){
		try {
			return Integer.parseInt(request.getParameter("iDisplayStart"));
		} catch (NumberFormatException ignore) {
			return 0;
		}
	}
	
	private int getRequestPagesize(HttpServletRequest request){
		try {
			return Integer.parseInt(request.getParameter("iDisplayLength"));
		} catch (NumberFormatException ignore) {
			return Integer.MAX_VALUE;
		}
	}

}
