package org.leadfar.egov.servlet;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.leadfar.egov.utils.BeansFactory;
import org.leadfar.egov.utils.DicConverter;


public class ApplicationServlet extends HttpServlet {
	private Log logger=LogFactory.getLog(ApplicationServlet.class);
	//private Logger logger = Logger.getLogger(ApplicationServlet.class);
	@Override
	public void init() throws ServletException {
		//logger.info("��������BeanFactory");
		System.out.println("��������BeanFactory"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ms").format(new Date()));
		BeansFactory.init();
		System.out.println("����BeanFactory����"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ms").format(new Date()));
		System.out.println("���ڳ�ʼ����ݴʵ�"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ms").format(new Date()));
		DicConverter.init();
		System.out.println("��ʼ����ݴʵ����"+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ms").format(new Date()));
	}

	
}
