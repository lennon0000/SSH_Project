package org.leadfar.egov.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.service.BusinessService;
import org.leadfar.egov.service.DicService;
import org.leadfar.egov.utils.JDBCHelper;
import org.leadfar.egov.utils.PageModelContext;

public class RecordServlet extends BaseServlet {

	private BusinessService businessService;

	public void setBusinessService(BusinessService businessService) {
		this.businessService = JDBCHelper.getProxy(businessService);
	}

	
	public void list(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");
		List<Business> businesses = new ArrayList<Business>();
		if (acceptNo == null || acceptNo.trim().equals("")) {
			acceptNo = new SimpleDateFormat("yyyyMMdd").format(new Date());
		}

		businesses = this.businessService.searchRecord(acceptNo,
				this.currentUser(request));

		request.setAttribute("acceptNo", acceptNo);
		request.setAttribute("pm", PageModelContext.getPageModel());
		request.setAttribute("businesses", businesses);
		request.getRequestDispatcher("record/list.jsp").forward(request,
				response);

	}


	public void finish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		this.businessService.finishRecord(acceptNo);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void release(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		this.businessService.releaseRecord(acceptNo);

		response.sendRedirect(request.getContextPath()
				+ "/common/updateSuccess.jsp");
	}

	public void lock(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acceptNo = request.getParameter("acceptNo");

		//this.businessService.lockRecord(acceptNo, this.currentUser(request));
		
		request.setAttribute("acceptNo", acceptNo);
		request.getRequestDispatcher("ep/main.jsp").forward(request, response);
//		response.sendRedirect(request.getContextPath()
//				+ "/register/ep/main.jsp");
	}

}
