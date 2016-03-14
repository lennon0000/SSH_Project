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
			enterpriseStatus.setCode("01");// TODO:�������õ�enterpriseStatusΪ01��չ��ʱӦ��dic�н��в�ѯ
			e.setEnterpriseStatus(enterpriseStatus);

			e.setRegisterNo(enterpriseDao.createRegisterNo(e));
			enterpriseDao.save(e);
			// ��enterprise�������ݿ��к�Ϊ�ö���������һ��registerNo����Ϊ��business���У������һ��enterpriseId��(���)��ͨ���������������enterprise��
			// ������service�е�����insert��Ҫ�������enterpriseId�ٸ�ֵ��business���С�
			// ������Ҫ��ø�enterprise�����id(Ϊ�˻��id���ý��������ֶ�ɾ��)�������ò��ҵķ�������ô������ݿ���������enterprise����Ȼ��������registerNo��
			// ������ֱ���ô�������e����Ϊ�����û��registerNo��
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
