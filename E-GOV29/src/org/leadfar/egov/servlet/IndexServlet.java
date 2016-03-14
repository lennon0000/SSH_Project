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
		request.getSession().removeAttribute("error");// 这句是防止在出现错误时，再次提交及时正确还是可能出先错误
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
			
			request.getSession().setAttribute("LOGIN_USER", loginUser);// 一定要注意在登录成功时，要设置一个session"LOGIN_USER"这样在后边就可以根据判断该session是否存在而确定是否登录
			response.sendRedirect(request.getContextPath() + "/main.html");
//要注意，这里成功后，还是转向main.html，在htlm中，他自己会调用center，然后调用到本来指向left.jsp的地，因为要显示菜单栏，所有加一层servlet，
		} catch (RuntimeException re) {

			request.getSession().setAttribute("error", re.getCause().getCause().getMessage());
			response.sendRedirect(request.getContextPath() + "/");
		}
	}

	public void menu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User)request.getSession().getAttribute("LOGIN_USER");
		List<Func> funcs = funcService.search();

		for (Func parent : funcs) {// 将获得的funcs进行遍历，核对在funcs中是否有与user的func一样的，一样的话，则应该为选中状态
			parent.setChecked(this.havePermission(parent.getId(),
					user.getFuncs()) ? "checked" : "");// 这里我们希望给parent set
														// 的checked为一个状态。即是否为checked，则通过调用方法进行判断
			for (Func child : parent.getChildren()) {
				child.setChecked(this.havePermission(child.getId(),
						user.getFuncs()) ? "checked" : "");
			}
		}
	//	request.getSession().removeAttribute("error");// 这句是防止在出现错误时，再次提交及时正确还是可能出先错误
		request.setAttribute("funcs", funcs);
		request.getRequestDispatcher("/frame/left.jsp").forward(
				request, response);
	}

	private boolean havePermission(String funcId, List<Func> permission) {// 这里比较巧妙，注意理解传入的参数
		for (Func parent : permission) {
			if (parent.getId().equals(funcId)) {
				return true;
			}
		}
		return false;
	}
	
	
	public void userCancel(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	//	request.getSession().removeAttribute("error");// 这句是防止在出现错误时，再次提交及时正确还是可能出先错误
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
			//request.getSession().removeAttribute("error");// 这句是防止在出现错误时，再次提交及时正确还是可能出先错误
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