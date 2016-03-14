package org.leadfar.egov.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.leadfar.egov.dao.EnterpriseDao;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.ep.Enterprise;
import org.leadfar.egov.model.ep.EnterpriseFile;
import org.leadfar.egov.model.ep.StockHolder;
import org.leadfar.egov.service.EnterpriseService;

public class EnterpriseServiceImpl implements EnterpriseService {

	private EnterpriseDao enterpriseDao;

	public void setEnterpriseDao(EnterpriseDao enterpriseDao) {
		this.enterpriseDao = enterpriseDao;
	}

	public void addEn(Enterprise e, String acceptNo) {
		int epId = e.getId();
		if(epId == 0){
			Dic enterpriseStatus = new Dic();
			enterpriseStatus.setCode("01");// TODO:这里设置的enterpriseStatus为01，展现时应从dic中进行查询
			e.setEnterpriseStatus(enterpriseStatus);

			e.setRegisterNo(enterpriseDao.createRegisterNo(e));
			enterpriseDao.save(e);
			// 将enterprise存入数据库中后，为该对象生成了一个registerNo，因为在business表中，添加了一个enterpriseId列(外键)，通过这个列来关联到enterprise表，
			// 所以在service中调用完insert后，要把这个即enterpriseId再赋值给business表中。
			// 所以需要获得该enterprise对象的id(为了获得id，得将触发器手动删除)，来调用查找的方法，获得存入数据库中完整的enterprise对象，然后获得他的registerNo，
			// （不能直接用传过来的e，因为这里边没有registerNo）
			int enterpriseId = e.getId();

			enterpriseDao.updateBusiness(acceptNo, enterpriseId);
		}else{
			Dic enterpriseStatus = this.enterpriseDao.get(e.getId()).getEnterpriseStatus();
			e.setEnterpriseStatus(enterpriseStatus);
			this.enterpriseDao.update(e);
			
		}
		

	}

	public Enterprise getById(int enterpriseId) {

		return this.enterpriseDao.get(enterpriseId);
	}

	public int getEnterpriseIdById(String acceptNo) {
		return this.enterpriseDao.getEnterpriseId(acceptNo);
	}

	public void addStockHolders(List<StockHolder> stockHolderList,
			int enterpriseId) {

		this.enterpriseDao.deleteStockHolders(enterpriseId);
		for (StockHolder stockHolder : stockHolderList) {
			this.enterpriseDao.saveStockHolders(stockHolder);
		}

	}

	public List<StockHolder> getStockHolderList(int enterpriseId) {
		List<StockHolder> stockHolderList = new ArrayList<StockHolder>();

		return this.enterpriseDao.getStockHolderList(enterpriseId);
	}

	public void addEnterpriseFile(EnterpriseFile ef) {
		this.enterpriseDao.save(ef);
		
	}

	public void delEnterpriseFile(int id) {
		this.enterpriseDao.delEnterpriseFile(id);
		
	}

	public EnterpriseFile findEnterpriseFileById(int id) {
		
		return this.enterpriseDao.getEnterpriseFileById(id);
	}
}
