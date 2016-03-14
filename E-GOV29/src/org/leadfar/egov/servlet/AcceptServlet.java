package org.leadfar.egov.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.Relation;
import org.leadfar.egov.service.BusinessService;
import org.leadfar.egov.service.DicService;
import org.leadfar.egov.utils.JDBCHelper;
import org.leadfar.egov.utils.PageModelContext;
import org.leadfar.egov.utils.RequestUtils;

public class AcceptServlet extends BaseServlet {
	private DicService dicService;
	private BusinessService businessService;

	public void setDicService(DicService dicService) {
		this.dicService = JDBCHelper.getProxy(dicService);
	}

	public void setBusinessService(BusinessService businessService) {
		this.businessService = JDBCHelper.getProxy(businessService);
	}

	@Override
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");
		List<Business> businesses = new ArrayList<Business>();
		if (acceptNo == null || acceptNo.trim().equals("")) {
			acceptNo = new SimpleDateFormat("yyyyMMdd").format(new Date());
		}

		businesses = this.businessService.search(acceptNo,
				this.currentUser(request));

		request.setAttribute("acceptNo", acceptNo);
		request.setAttribute("pm", PageModelContext.getPageModel());
		request.setAttribute("businesses", businesses);
		request.getRequestDispatcher("business/list.jsp").forward(request,
				response);

	}

	public void addInput(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Dic> businessType = new ArrayList<Dic>();
		businessType = dicService.search("BusinessType");
		request.setAttribute("businessTypes", businessType);
		request.getRequestDispatcher("/business/add_input.jsp").forward(
				request, response);

	}

	@Override
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Business business = new Business();
		Relation relation = new Relation();

		RequestUtils.copyParamsToModel(request, business);
		RequestUtils.copyParamsToModel(request, relation);
		business.setRelation(relation);
		// User user = (User)request.getSession().getAttribute("LOGIN_USER");
		try {
			this.businessService.add(business, this.currentUser(request));
			String infor = "数据添加成功";
			request.setAttribute("infor", infor);

			List<Dic> businessType2 = new ArrayList<Dic>();
			businessType2 = dicService.search("BusinessType");
			request.setAttribute("businessTypes", businessType2);
			request.getRequestDispatcher("/business/add_input.jsp").forward(
					request, response);

		} catch (RuntimeException re) {
			request.setAttribute("error", re.getCause().getCause().getMessage());
			System.out.println(re.getCause().getCause().getMessage());
		}

	}

	public void finish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		this.businessService.finish(acceptNo);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void release(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		this.businessService.release(acceptNo);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void lock(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		this.businessService.lock(acceptNo, this.currentUser(request));

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void look(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		//Business b = this.businessService.getById(acceptNo);
		Business b = this.businessService.getById(acceptNo);
		//因为在service中，对business对象的processes属性进行了设值，所以在这里和转向的页面中就能获得相应参数
		request.setAttribute("business", b);
		request.getRequestDispatcher("business/detail.jsp").forward(request,
				response);

	}
}
