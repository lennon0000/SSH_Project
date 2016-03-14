package org.leadfar.egov.service.impl;

import java.util.List;

import org.leadfar.egov.dao.FuncDao;
import org.leadfar.egov.dao.impl.FuncDaoImpl;
import org.leadfar.egov.model.Func;
import org.leadfar.egov.service.FuncService;

public class FuncServiceImpl implements FuncService {
	private FuncDao funcDao;
	public void setFuncDao(FuncDao funcDao) {
		this.funcDao = funcDao;
	}
	public List<Func> search() {
		
		return funcDao.query();
	}

}
