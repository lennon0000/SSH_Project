package org.leadfar.egov.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.model.Func;
import org.leadfar.egov.model.User;
import org.leadfar.egov.service.DicService;
import org.leadfar.egov.service.FuncService;
import org.leadfar.egov.service.UserService;
import org.leadfar.egov.service.impl.DicServiceImpl;
import org.leadfar.egov.service.impl.FuncServiceImpl;
import org.leadfar.egov.service.impl.UserServiceImpl;
import org.leadfar.egov.utils.BeansFactory;
import org.leadfar.egov.utils.JDBCHelper;

public class IndexServlet extends BaseServlet {
	private UserService userService;
	private DicService dicService;
	private FuncService funcService;
	
	public void setUserService(UserService userService) {
		this.userService = JDBCHelper.getProxy(userService);
	}

	public void setDicService(DicService dicService) {
		this.dicService = JDBCHelper.getProxy(dicService);
	}

	public void setFuncService(FuncService funcService) {
		this.funcService = JDBCHelper.getProxy(funcService);
	}

	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String seletedAdmcode = "";
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("seleted_admcode")){
					seletedAdmcode =cookie.getValue();
					break;
				}
			}
		}
		request.setAttribute("seletedAdmcode",seletedAdmcode);
		request.setAttribute("adms", this.dicService.search("AdmName"));
		request.getRequestDispatcher("/login.jsp").forward(request, response);
		request.getSession().removeAttribute("error");// ����Ƿ�ֹ�ڳ��ִ���ʱ���ٴ��ύ��ʱ��ȷ���ǿ��ܳ��ȴ���
	}

	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String admcode = request.getParameter("admcode");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String ip = request.getRemoteAddr();
		try {

			User loginUser = this.userService.login(admcode, username,
					password, ip);
			
			Cookie c = new Cookie("seleted_admcode", admcode);
			c.setPath(request.getContextPath());
			c.setMaxAge(60*60*24);
			response.addCookie(c);
			
			request.getSession().setAttribute("LOGIN_USER", loginUser);// һ��Ҫע���ڵ�¼�ɹ�ʱ��Ҫ����һ��session"LOGIN_USER"�����ں�߾Ϳ��Ը����жϸ�session�Ƿ���ڶ�ȷ���Ƿ��¼
			response.sendRedirect(request.getContextPath() + "/main.html");
//Ҫע�⣬����ɹ��󣬻���ת��main.html����htlm�У����Լ������center��Ȼ����õ�����ָ��left.jsp�ĵأ���ΪҪ��ʾ�˵��������м�һ��servlet��
		} catch (RuntimeException re) {

			request.getSession().setAttribute("error", re.getCause().getCause().getMessage());
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	public void menu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("LOGIN_USER");
		List<Func> funcs = funcService.search();

		for (Func parent : funcs) {// ����õ�funcs���б������˶���funcs���Ƿ�����user��funcһ���ģ�һ���Ļ�����Ӧ��Ϊѡ��״̬
			parent.setChecked(this.havePermission(parent.getId(),
					user.getFuncs()) ? "checked" : "");// ��������ϣ����parent set
														// ��checkedΪһ��״̬�����Ƿ�Ϊchecked����ͨ�����÷��������ж�
			for (Func child : parent.getChildren()) {
				child.setChecked(this.havePermission(child.getId(),
						user.getFuncs()) ? "checked" : "");
			}
		}
	//	request.getSession().removeAttribute("error");// ����Ƿ�ֹ�ڳ��ִ���ʱ���ٴ��ύ��ʱ��ȷ���ǿ��ܳ��ȴ���
		request.setAttribute("funcs", funcs);
		request.getRequestDispatcher("/frame/left.jsp").forward(
				request, response);
	}

	private boolean havePermission(String funcId, List<Func> permission) {// ����Ƚ����ע����⴫��Ĳ���
		for (Func parent : permission) {
			if (parent.getId().equals(funcId)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void userCancel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	//	request.getSession().removeAttribute("error");// ����Ƿ�ֹ�ڳ��ִ���ʱ���ٴ��ύ��ʱ��ȷ���ǿ��ܳ��ȴ���
		request.getSession().removeAttribute("LOGIN_USER");
		response.sendRedirect(request.getContextPath()
				+ "/");
	}
	public void modifyPassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		User loginUser = (User)request.getSession().getAttribute("LOGIN_USER");
		int loginId = loginUser.getId();
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		
		try {
			
			this.userService.modifyPassword(loginUser,password1,password2);
			//request.getSession().removeAttribute("error");// ����Ƿ�ֹ�ڳ��ִ���ʱ���ٴ��ύ��ʱ��ȷ���ǿ��ܳ��ȴ���
			User user = (User)request.getSession().getAttribute("LOGIN_USER");
			user.setStatus("01");
			request.getSession().setAttribute("LOGIN_USER", user);
			response.sendRedirect(request.getContextPath() + "/main.html");
			
		} catch (RuntimeException re) {

			//request.getSession().setAttribute("error", re.getMessage());
			request.setAttribute("error",  re.getCause().getCause().getMessage());
		//	response.sendRedirect(request.getContextPath() + "/main.html");
			request.getRequestDispatcher("/system/user/modifyPassword_input.jsp").forward(request, response);
		}
	}
	public void getIp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ip = request.getRemoteAddr();
		request.setAttribute("ip", ip);
		
		request.getRequestDispatcher("/system/user/bindingIp.jsp").forward(request, response);
	}
	public void bindingIp(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String ip = request.getParameter("ip");
		User user = (User)request.getSession().getAttribute("LOGIN_USER");
		user.setIp(ip);
		this.userService.modify(user);
		//this.userService.binding(ip,user);
		response.sendRedirect(request.getContextPath()+"/common/bindingIpSuccess.jsp");
		//request.getRequestDispatcher("/user/bindingIp.jsp").forward(request, response);
	}
}