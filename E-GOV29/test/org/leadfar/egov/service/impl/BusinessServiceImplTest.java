package org.leadfar.egov.service.impl;

import java.util.Date;

import junit.framework.TestCase;

import org.leadfar.egov.dao.BusinessDao;
import org.leadfar.egov.dao.ProcessDao;
import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.BusinessProcess;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.Relation;
import org.leadfar.egov.model.User;
import org.leadfar.egov.service.BusinessService;
import org.leadfar.egov.utils.BeansFactory;
import org.leadfar.egov.utils.JDBCHelper;
import org.leadfar.egov.utils.PageModelContext;

public class BusinessServiceImplTest extends TestCase {
	private BusinessService bs;
	
	@Override
	protected void setUp() throws Exception {
		
		bs=JDBCHelper.getProxy((BusinessService)BeansFactory.getBean("businessService"));
	}
	public void testAdd(){
//		Business b = new Business();
//		Dic businessType = new Dic();
//		businessType.setCode("01");
//		b.setBusinessType(businessType);
//		b.setEnterpriseName("测试企业");
//		
//		Relation r = new Relation();
//		r.setEmail("a");
//		r.setIdNumber("1");
//		r.setName("b");
//		r.setPhone("111");
//		b.setRelation(r);
//		
//		
//		User user = new User();
//		Dic adm = new Dic();
//		adm.setCode("1");
//		adm.setValue("1");
//		user.setAdm(adm);
//		
//		bs.add(b, user);
		
		Business b=new Business();
		Dic businessType=new Dic();
		businessType.setCode("01");
		
		b.setBusinessType(businessType);
		b.setEnterpriseName("测试企业1");
		
		Relation r=new Relation();
		r.setEmail("a");
		r.setIdNumber("a");
		r.setName("a");
		r.setPhone("a");
		
		b.setRelation(r);
		
		User user=new User();
		user.setId(21);
		
		Dic adm=new Dic();
		adm.setCode("110100");
		user.setAdm(adm);
		
		bs.add(b, user);

	}
	public void testSearch(){
		PageModelContext.setPageModel(new PageModel());
		User user=new User();
		user.setId(42);
		Dic adm=new Dic();
		adm.setCode("110100");
		user.setAdm(adm);
		System.out.println(bs.search("", user));
	}

	
	
}
