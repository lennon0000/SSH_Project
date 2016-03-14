package org.leadfar.egov.service;

import java.util.List;

import org.leadfar.egov.model.ep.Enterprise;
import org.leadfar.egov.model.ep.EnterpriseFile;
import org.leadfar.egov.model.ep.StockHolder;

public interface EnterpriseService {

	public void addEn(Enterprise e,String acceptNo);

	public Enterprise getById(int enterpriseId );

	public int getEnterpriseIdById(String acceptNo);

	public void addStockHolders(List<StockHolder> stockHolderList,int enterpriseId);

	public List<StockHolder> getStockHolderList(int enterpriseId);

	public void addEnterpriseFile(EnterpriseFile ef);

	public void delEnterpriseFile(int id);

	public EnterpriseFile findEnterpriseFileById(int id);
	
}

