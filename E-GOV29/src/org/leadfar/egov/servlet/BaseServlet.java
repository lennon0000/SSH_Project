package org.leadfar.egov.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.User;
import org.leadfar.egov.utils.BeansFactory;
import org.leadfar.egov.utils.PageModelContext;

//Servlet的通用控制模板
public class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String m = request.getParameter("m"); // 请求操作标识
		if (m == null || "".equals(m))
			m = "list";
		
		PageModel pm = new PageModel();
		if (request.getParameter("pageNo") != null) {
				pm.setPageNo(Integer.parseInt(request.getParameter("pageNo")));
			
		}
		PageModelContext.setPageModel(pm);
		
		Method[] methods  = this.getClass().getMethods();
		
		for (Method method : methods) {
			if(method.getName().startsWith("set")&&method.getName().endsWith("Service")){
				String propertyname = method.getName().substring(3,4).toLowerCase()+method.getName().substring(4);
			try {
				method.invoke(this, BeansFactory.getBean(propertyname));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
		
		
		
		
		
		try {
			Method method = this.getClass().getMethod(m,
					HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, request, response);
		} catch (SecurityException e) {
		
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
		
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
		
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
		
			e.printStackTrace();
		}
		PageModelContext.removePageModel();
	}
	//列表显示 默认操作 
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	//更新操作
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	//编辑操作
	public void updateInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	//添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	//添加输入
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
	//删除
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	public User currentUser(HttpServletRequest request)
	throws ServletException, IOException {
		return (User)request.getSession().getAttribute("LOGIN_USER");
}
}
