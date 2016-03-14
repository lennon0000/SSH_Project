package org.leadfar.egov.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.model.Func;
import org.leadfar.egov.model.User;

public class PermissionCheckFilter implements Filter {

	public void destroy() {

	}
	

	public void doFilter(ServletRequest req, ServletResponse resp,
	FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		User loginUser = (User)request.getSession().getAttribute("LOGIN_USER");
		String url = request.getRequestURI();
		if(url.endsWith("Servlet")){//ע�⣺ֻ�е���ַ��servlet��βʱ�Ž��м��
			if(loginUser!=null){
				if (!this.check(url,loginUser.getFuncs())) {
					
					response.sendRedirect(request.getContextPath()+"/common/noPermission.jsp");
						return ;
					}
			}
			
		}
		

		chain.doFilter(req, resp);

	}

	private boolean check(String url, List<Func> permission) {
		for (Func f : permission) {
			
			//for (Func func : f.getChildren()) {
				if((f.getUrl()!=null)){//ע�⣬һ��Ҫ���������������,��Ϊ����funcsΪ�������������Ķ��еĹ��ܼ��ϣ������û��url�����׳��쳣
					if(url.endsWith(f.getUrl())){
						return true;
					}
				}
		//	}
			
			
		}
		return false;
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
