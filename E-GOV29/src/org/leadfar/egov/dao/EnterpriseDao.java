package org.leadfar.egov.dao;

import java.util.List;

import org.leadfar.egov.model.ep.Enterprise;
import org.leadfar.egov.model.ep.EnterpriseFile;
import org.leadfar.egov.model.ep.StockHolder;

public interface EnterpriseDao {
	public void save(Enterprise e);
	public String createRegisterNo(Enterprise e);
	public Enterprise get(int id);
	public void update(Enterprise e);
	public void updateBusiness(String acceptNo,int enterpriseId);
	public int getEnterpriseId(String acceptNo);
	public void saveStockHolders(StockHolder stockHolder);
	public void deleteStockHolders(int enterpriseId);
	public List<StockHolder> getStockHolderList(int enterpriseId);
	public void save(EnterpriseFile ef);
	public void delEnterpriseFile(int id);
	public EnterpriseFile getEnterpriseFileById(int id);
}

