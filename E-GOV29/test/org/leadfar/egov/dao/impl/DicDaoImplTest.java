package org.leadfar.egov.dao.impl;

import junit.framework.TestCase;

import org.leadfar.egov.dao.DicDao;
import org.leadfar.egov.model.Dic;

public class DicDaoImplTest extends TestCase {
	private DicDao dicDao=new DicDaoImpl();
	public void testDelete() {
		this.dicDao.delete(1);
	}

	public void testGet() {
		Dic dic=this.dicDao.get(2);
		System.out.println(dic);
	}

	public void testQuery() {
		System.out.println(this.dicDao.query("Main"));
	}

	public void testSave() {
		
		 Dic dic=new Dic();
		dic.setCode("EnterpriseStatus");
		dic.setValue("企业状态");
		dic.setType("Main");
		dicDao.save(dic);
		
	}

	public void testUpdate() {
		Dic dic=new Dic();
		dic.setId(1);
		dic.setCode("EnterpriseType");
		dic.setValue("企业类型11");
		dic.setType("Main");
		dicDao.update(dic);
	}

}
