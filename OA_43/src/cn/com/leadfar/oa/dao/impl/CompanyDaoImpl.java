package cn.com.leadfar.oa.dao.impl;

import org.springframework.stereotype.Repository;

import cn.com.leadfar.oa.dao.CompanyDao;
import cn.com.leadfar.oa.model.Company;
@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl implements CompanyDao {

	@Override//重写父类的方法，
	public void update(Company company) {
		
		Company party = getById(Company.class, company.getId());
		company.setParent(party.getParent());//将原来对象中除了能从jsp页面获得的都赋值给已经更改的company对象
		company.setChildren(party.getChildren());
		getSession().evict(party);
		getSession().update(company);
	}

	
	
}
