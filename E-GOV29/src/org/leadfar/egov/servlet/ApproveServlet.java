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
import org.leadfar.egov.service.BusinessService;
import org.leadfar.egov.service.DicService;
import org.leadfar.egov.utils.JDBCHelper;
import org.leadfar.egov.utils.PageModelContext;



public class ApproveServlet extends BaseServlet {
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

		businesses = this.businessService.searchApprove(acceptNo,
				this.currentUser(request));

		request.setAttribute("acceptNo", acceptNo);
		request.setAttribute("pm", PageModelContext.getPageModel());
		request.setAttribute("businesses", businesses);
		request.getRequestDispatcher("record/approve_list.jsp").forward(request,
				response);

	}public void finish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");
		String suggestion = request.getParameter("suggestion");
		
		this.businessService.finishApprove(acceptNo,suggestion);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");
		String suggestion = request.getParameter("suggestion");
		
		this.businessService.backApprove(acceptNo,suggestion);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void release(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		this.businessService.releaseApprove(acceptNo);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void lock(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		this.businessService.lockApprove(acceptNo, this.currentUser(request)); 

		request.setAttribute("business", this.businessService.getById(acceptNo));
		request.getRequestDispatcher("record/approve_input.jsp").forward(request,
				response);
	}
}
