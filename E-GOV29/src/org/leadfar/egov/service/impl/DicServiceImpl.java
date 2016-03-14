package org.leadfar.egov.service.impl;

import java.util.List;

import org.leadfar.egov.dao.DicDao;
import org.leadfar.egov.dao.impl.DicDaoImpl;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.service.DicService;

public class DicServiceImpl implements DicService {
	private DicDao dicDao;

	public void setDicDao(DicDao dicDao) {
		this.dicDao = dicDao;
	}

	public void add(Dic dic) {
		this.dicDao.save(dic);
	}

	public void modify(Dic dic) {
		this.dicDao.update(dic);

	}

	public void delete(int id) {
		this.dicDao.delete(id);

	}

	public Dic getById(int id) {
		
		return this.dicDao.get(id);
	}

	public List<Dic> search(String type) {
		
		return this.dicDao.query(type);
	}

}
