package org.leadfar.egov.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.model.Dic;
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
import org.leadfar.egov.utils.PageModelContext;
import org.leadfar.egov.utils.RequestUtils;

public class UserServlet extends BaseServlet {
	private UserService userService ;
	private DicService dicService ;
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

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String admcode = request.getParameter("admcode");
		if (admcode == null)
			admcode = "";

		String username = request.getParameter("username");

		if (username == null)
			username = "";

		request.setAttribute("users",
				this.userService.search(admcode, username));
		request.setAttribute("adms", dicService.search("AdmName"));
		request.setAttribute("pm", PageModelContext.getPageModel());
		request.getRequestDispatcher("/system/user/list.jsp").forward(request,
				response);

	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// String username = request.getParameter("username");
		// String name = request.getParameter("name");
		// String admcode = request.getParameter("admcode");
		// String dept = request.getParameter("dept");
		//
		//
		// user.setUsername(username);
		// user.setName(name);
		// Dic adm =new Dic();
		// adm.setCode(admcode);
		// user.setAdm(adm);
		// user.setDept(dept);
		User user = new User();
		RequestUtils.copyParamsToModel(request, user);

		this.userService.add(user);

		response.sendRedirect(request.getContextPath()
				+ "/common/saveSuccess.jsp");
	}

	@Override
	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Dic> adms = new ArrayList<Dic>();
		adms = dicService.search("AdmName");
		request.setAttribute("adms", adms);
		request.getRequestDispatcher("/system/user/add_input.jsp").forward(
				request, response);
	}

	@Override
	public void updateInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		List<Dic> adms = new ArrayList<Dic>();
		adms = dicService.search("AdmName");
		request.setAttribute("adms", adms);

		User user = new User();
		user = this.userService.getById(id);

		request.setAttribute("user", user);

		request.getRequestDispatcher("/system/user/update_input.jsp").forward(
				request, response);

	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getById(id);

		RequestUtils.copyParamsToModel(request, user);
		this.userService.modify(user);
		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void enable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		// User user = this.userService.getById(id);

		this.userService.enable(id);
		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void disable(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		// User user = this.userDao.get(id);

		this.userService.disable(id);
		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void listFunc(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		User user = userService.getById(id);
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

		request.setAttribute("funcs", funcs);
		request.getRequestDispatcher("/system/user/list_func.jsp").forward(
				request, response);
	}

	// public void listUserFunc(HttpServletRequest
	// request,//TODO:������Գ�����//������ϱߵĲ�ͬ�������Ϊ�˻�ø��û�����ӵ�е�Ȩ��ʱ������߲ٿ���չ�ֵģ����Խ�������ϱߵĺϲ��������������һ����
	// HttpServletResponse response) throws ServletException, IOException {
	//
	//
	// int id = Integer.parseInt(request.getParameter("id"));
	// User user = userService.getById(id);
	// List<Func> funcs = funcService.search();
	//
	// for (Func parent : funcs) {//
	// ����õ�funcs���б������˶���funcs���Ƿ�����user��funcһ���ģ�һ���Ļ�����Ӧ��Ϊѡ��״̬
	// parent.setChecked(this.havePermission(parent.getId(),
	// user.getFuncs()) ? "checked" : "");// ��������ϣ����parent set
	// // ��checkedΪһ��״̬�����Ƿ�Ϊchecked����ͨ�����÷��������ж�
	// for (Func child : parent.getChildren()) {
	// child.setChecked(this.havePermission(child.getId(),
	// user.getFuncs()) ? "checked" : "");
	// }
	// }
	// request.setAttribute("id", id);
	// request.setAttribute("funcs", funcs);
	// request.getRequestDispatcher("/frame/left.jsp").forward(//TODO:����Ӧ���ض�����main.jsp�е�left
	// request, response);
	// }

	private boolean havePermission(String funcId, List<Func> permission) {// ����Ƚ����ע����⴫��Ĳ���
		for (Func parent : permission) {
			if (parent.getId().equals(funcId)) {
				return true;
			}
		}
		return false;
	}

	public void addPermission(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("userId"));
		String[] funcIds = request.getParameterValues("funcIds");
		this.userService.addPermissionByUser(userId, funcIds);
		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	
}
