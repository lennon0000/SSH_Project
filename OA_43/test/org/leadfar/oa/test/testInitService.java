package org.leadfar.oa.test;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.service.InitService;
import cn.com.leadfar.oa.service.MenuService;
import cn.com.leadfar.oa.service.RoleService;

public class testInitService extends TestCase {
	public void testInitService() {
		BeanFactory factory = new ClassPathXmlApplicationContext(
				"applicationContext-*.xml");
		InitService is = (InitService) factory.getBean("initService");
		is.addInitDatas();
		//添加role
		
	}
	
}
