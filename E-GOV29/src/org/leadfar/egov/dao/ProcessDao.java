package org.leadfar.egov.dao;

import java.util.List;

import org.leadfar.egov.model.BusinessProcess;

public interface ProcessDao {
	public void save(BusinessProcess bp);

	public BusinessProcess get(String id);

	public void update(BusinessProcess bp);

	public void delete(String id);
	
	public List<BusinessProcess> query(String acceptNo) ;
}
