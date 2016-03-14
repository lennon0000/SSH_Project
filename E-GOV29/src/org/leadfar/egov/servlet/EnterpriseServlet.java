package org.leadfar.egov.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.ep.Enterprise;
import org.leadfar.egov.model.ep.EnterpriseFile;
import org.leadfar.egov.model.ep.StockHolder;
import org.leadfar.egov.service.BusinessService;
import org.leadfar.egov.service.DicService;
import org.leadfar.egov.service.EnterpriseService;
import org.leadfar.egov.utils.JDBCHelper;
import org.leadfar.egov.utils.RequestUtils;
import org.leadfar.egov.utils.SqlSessionHelper;

public class EnterpriseServlet extends BaseServlet {

	private DicService dicService;
	private EnterpriseService enterpriseService;
	private BusinessService businessService;

	public void setBusinessService(BusinessService businessService) {
		this.businessService = JDBCHelper.getProxy(businessService);
	}

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = SqlSessionHelper.getProxy(enterpriseService);
	}// TODO:这里应该改为SessionHelper代理

	public void setDicService(DicService dicService) {
		this.dicService = JDBCHelper.getProxy(dicService);
	}

	@SuppressWarnings("unused")
	public void listEn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acceptNo = request.getParameter("acceptNo");
		String statuscode = this.businessService.getById(acceptNo)
				.getProcessStatus().getCode();
		if (this.businessService.getById(acceptNo).getProcessStatus().getCode()
				.equals("02")) {
			// this.businessService.lockRecord(acceptNo,
			request.setAttribute("Id", 0);

		} else {
			int enterpriseId = this.enterpriseService
					.getEnterpriseIdById(acceptNo);
			Enterprise e = this.enterpriseService.getById(enterpriseId);
			request.setAttribute("enterprise", e);
			request.setAttribute("Id", e.getId());
			request.getSession().setAttribute("enterpriseId", enterpriseId);
			// request.setAttribute("acceptNo", acceptNo);
			// request.setAttribute("enterpriseTypes",
			// dicService.search("EnterpriseType"));
			// request.getRequestDispatcher("basic_info.jsp").forward(request,
			// response);
		}
		request.setAttribute("acceptNo", acceptNo);
		request.setAttribute("enterpriseTypes",
				dicService.search("EnterpriseType"));
		request.getRequestDispatcher("basic_info.jsp").forward(request,
				response);
	}

	public void addEn(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int Id = Integer.parseInt(request.getParameter("Id"));
		String acceptNo = request.getParameter("acceptNo");
		Enterprise e = new Enterprise();
		RequestUtils.copyParamsToModel(request, e);

		Dic adm = new Dic();
		this.currentUser(request).getAdm();
		adm = this.currentUser(request).getAdm();
		e.setAdm(adm);
		e.setId(Id);
		request.setAttribute("acceptNo", acceptNo);
		this.enterpriseService.addEn(e, acceptNo);
		this.businessService.lockRecord(acceptNo, this.currentUser(request));

		String step = (String) request.getSession().getAttribute("step");
		step = "over";
		request.getSession().setAttribute("step", step);

		request.getRequestDispatcher("/register/ep/EnterpriseServlet?m=listEn")
				.forward(request, response);

		// response.sendRedirect(request.getContextPath()
		// + "/register/ep/stock_holder.jsp");

	}

	public void addStockHolders(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String acceptNo = request.getParameter("acceptNo");
		int enterpriseId = this.enterpriseService.getEnterpriseIdById(acceptNo);
		String stockHolderMsg = request.getParameter("stockHolderMsg");
		String[] stockHolders = stockHolderMsg.split(";;");

		List<StockHolder> stockHolderList = new ArrayList<StockHolder>();

		for (String stockHolder : stockHolders) {
			String[] stockHolderInfo = stockHolder.split("::");
			String name = stockHolderInfo[0];
			String cardNo = stockHolderInfo[1];
			float moneyInvested = Float.parseFloat(stockHolderInfo[2]);
			float percentage = Float.parseFloat(stockHolderInfo[3]);

			StockHolder sh = new StockHolder();
			sh.setName(name);
			sh.setCardNo(cardNo);
			sh.setMoneyInvested(moneyInvested);
			sh.setPercentage(percentage);
			sh.setEnterpriseId(enterpriseId);
			stockHolderList.add(sh);
		}

		this.enterpriseService.addStockHolders(stockHolderList, enterpriseId);
		request.setAttribute("acceptNo", acceptNo);
		request.getRequestDispatcher("/register/ep/EnterpriseServlet?m=listSh")
				.forward(request, response);

	}

	public void listSh(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acceptNo = request.getParameter("acceptNo");

		int enterpriseId = this.enterpriseService.getEnterpriseIdById(acceptNo);
		if (this.enterpriseService.getStockHolderList(enterpriseId).size() != 0) {
			List<StockHolder> stockHolderList = this.enterpriseService
					.getStockHolderList(enterpriseId);
			request.setAttribute("stockHolderList", stockHolderList);
		}

		request.setAttribute("acceptNo", acceptNo);
		request.getRequestDispatcher("stock_holder.jsp").forward(request,
				response);
	}

	public void addEF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int enterpriseId = Integer.parseInt(request
				.getParameter("enterpriseId"));
		int length = Integer.parseInt(request.getParameter("length"));
		String fileName = URLDecoder.decode(request.getParameter("fileName"),
				"utf-8");
		String path = URLDecoder.decode(request.getParameter("path"), "utf-8");

		EnterpriseFile ef = new EnterpriseFile();
		ef.setEnterpriseId(enterpriseId);
		ef.setLength(length);
		ef.setName(fileName);
		ef.setPath(path);

		this.enterpriseService.addEnterpriseFile(ef);

		response.sendRedirect(request.getContextPath()
				+ "/register/ep/EnterpriseServlet?m=listFiles&enterpriseId="
				+ enterpriseId);

	}

	public void delEF(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		EnterpriseFile ef=this.enterpriseService.findEnterpriseFileById(id);
		File f=new File(ef.getPath());
		if(f.exists()){
			f.delete();
		}
		
		this.enterpriseService.delEnterpriseFile(id);

		
	}
}
