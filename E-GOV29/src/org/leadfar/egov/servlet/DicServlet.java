package org.leadfar.egov.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.dao.DicDao;
import org.leadfar.egov.dao.impl.DicDaoImpl;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.service.DicService;
import org.leadfar.egov.service.FuncService;
import org.leadfar.egov.service.UserService;
import org.leadfar.egov.service.impl.DicServiceImpl;
import org.leadfar.egov.service.impl.FuncServiceImpl;
import org.leadfar.egov.service.impl.UserServiceImpl;
import org.leadfar.egov.utils.JDBCHelper;
import org.leadfar.egov.utils.RequestUtils;

public class DicServlet extends BaseServlet {

	private DicDao dicDao;
	private DicService dicService;
	
	public void setDicDao(DicDao dicDao) {
			this.dicDao = dicDao;
	}

	public void setDicService(DicService dicService) {
		this.dicService = JDBCHelper.getProxy(dicService);
	}

	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter("type");
		if (type == null || "".equals(type)) {
			type = "main";
		}
		request.setAttribute("dics", dicService.search(type));
		request.setAttribute("type", type);

		request.getRequestDispatcher("/system/dic/list.jsp").forward(request,
				response);
	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Dic dic = new Dic();
//		dic.setCode(request.getParameter("code"));
//		dic.setValue(request.getParameter("value"));
//		dic.setType(request.getParameter("type"));
		RequestUtils.copyParamsToModel(request, dic);
		this.dicService.add(dic);

		response.sendRedirect(request.getContextPath()
				+ "/common/saveSuccess.jsp");
	}

	@Override
	public void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Dic dic = new Dic();

//		dic.setCode(request.getParameter("code"));
//		dic.setValue(request.getParameter("value"));
//		dic.setType(request.getParameter("type"));
//		dic.setId(id);
		RequestUtils.copyParamsToModel(request, dic);
		

		this.dicService.modify(dic);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	@Override
	public void updateInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));

		// String type = request.getParameter("type");

		Dic dic = this.dicService.getById(id);

		request.setAttribute("dic", dic);

		request.getRequestDispatcher("/system/dic/update_input.jsp").forward(
				request, response);
	}

	@Override
	public void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		this.dicService.delete(id);
		response.sendRedirect(request.getContextPath()
				+ "/common/deleteSuccess.jsp");
	}

}
