package org.leadfar.egov.service;

import java.util.Date;
import java.util.List;

import org.leadfar.egov.dao.BusinessDao;
import org.leadfar.egov.dao.ProcessDao;
import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.BusinessProcess;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.User;

public class BaseBusinessService {
	protected BusinessDao businessDao;
	protected ProcessDao processDao;

	
	
	

	
	public void finish(String acceptNo,String status,String step) {
		Business b = this.businessDao.get(acceptNo);

		Dic processStatus = new Dic();
		processStatus.setCode(status);
		b.setProcessStatus(processStatus);

		this.businessDao.update(b);

		BusinessProcess bp = this.processDao.get(acceptNo + "_"+step);
		bp.setEndTime(new Date());

		this.processDao.update(bp);

	}

	public void release(String acceptNo,String status,String step) {
		Business b = this.businessDao.get(acceptNo);

		Dic processStatus = new Dic();
		processStatus.setCode(status);
		b.setProcessStatus(processStatus);
		this.businessDao.update(b);
		
		this.processDao.delete(acceptNo + "_"+step);
		
	}


	public void lock(String acceptNo, User user,String status,String step) {
		Business b = this.businessDao.get(acceptNo);
		b.setAdm(user.getAdm());
		b.setCreateTime(new Date());

		Dic processStatus = new Dic();
		processStatus.setCode(status);
		b.setProcessStatus(processStatus);

		
		businessDao.update(b);
		//businessDao.saveRelation(b.getRelation(), b.getAcceptNo());
		// 这是从页面获取的，后边的是从BusinessDao中生成的
		BusinessProcess bp = this.processDao.get(b.getAcceptNo() + "_"+step);
		if(bp==null){
			bp = new BusinessProcess();
			bp.setId(b.getAcceptNo() + "_"+step);
			bp.setStep(step);
			bp.setStartTime(new Date());
			bp.setUser(user);
			processDao.save(bp);
		}else{
			bp.setUser(user);
			bp.setEndTime(null);
			bp.setSuggestion(null);
		}
		
		
		
	}

	public void finish(String acceptNo, String status, String step,
			String suggestion) {
		Business b = this.businessDao.get(acceptNo);

		Dic processStatus = new Dic();
		processStatus.setCode(status);
		b.setProcessStatus(processStatus);
		this.businessDao.update(b);

		BusinessProcess bp = this.processDao.get(acceptNo + "_"+step);
		bp.setEndTime(new Date());
		bp.setSuggestion(suggestion);
		this.processDao.update(bp);

		
	}
}
