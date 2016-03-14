package cn.com.leadfar.oa.service;

import cn.com.leadfar.oa.model.Company;
import cn.com.leadfar.oa.model.Party;

public interface CompanyService {
	public Company getCompany(int companyId);

	public void save(Party party);

	public void update(Company company);
}
