package org.leadfar.egov.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.leadfar.egov.dao.BusinessDao;
import org.leadfar.egov.dao.ProcessDao;
import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.BusinessProcess;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.User;
import org.leadfar.egov.service.BaseBusinessService;
import org.leadfar.egov.service.BusinessService;

public class BusinessServiceImpl extends BaseBusinessService implements BusinessService {

	public void setBusinessDao(BusinessDao businessDao) {
		this.businessDao = businessDao;
	}

	public void setProcessDao(ProcessDao processDao) {
		this.processDao = processDao;
	}

	public void add(Business b, User user) {
		b.setAdm(user.getAdm());
		b.setCreateTime(new Date());

		Dic processStatus = new Dic();
		processStatus.setCode("01");
		b.setProcessStatus(processStatus);

		b.setAcceptNo(businessDao.createAcceptNo());
		
		businessDao.save(b);
		businessDao.saveRelation(b.getRelation(), b.getAcceptNo());
		// 这是从页面获取的，后边的是从BusinessDao中生成的
		BusinessProcess bp = new BusinessProcess();
		bp.setId(b.getAcceptNo() + "_A");
		bp.setStep("A");
		bp.setStartTime(new Date());
		bp.setUser(user);
		processDao.save(bp);

	}

	//信息录入

	public void lockRecord(String acceptNo, User currentUser) {
		super.lock(acceptNo, currentUser, "03", "B");
		
		
	}

	public void releaseRecord(String acceptNo) {
		super.release(acceptNo, "02", "A");
		
	}

	public void finishRecord(String acceptNo) {
		super.finish(acceptNo, "04", "B");
		
	}
	
	
	public List<Business> searchRecord(String acceptNo, User user) {
		return businessDao.query(acceptNo, user,"B","'03','06'","02");
	}
	//受理登记

	public void finish(String acceptNo) {
		super.finish(acceptNo, "02", "A");
		
	}

	public void release(String acceptNo) {
		super.release(acceptNo, "00", "A");
		
	}

	public void lock(String acceptNo, User user) {
		super.lock(acceptNo, user, "01", "A");
		
	}

	public List<Business> search(String acceptNo, User user) {
	//	
		return businessDao.query(acceptNo, user);
	}

//	主管审查
	public void lockCheck(String acceptNo, User user) {
		super.lock(acceptNo, user, "05", "C");
		
	}

	public void releaseCheck(String acceptNo) {
		super.release(acceptNo, "04", "B");
		
	}

//	public void finishCheck(String acceptNo) {
//		super.finish(acceptNo, "06", "C");
//		
//	}

	public List<Business> searchCheck(String acceptNo, User user) {
		return businessDao.query(acceptNo, user,"B","04","05");
	}

	public Business getById(String acceptNo) {
		Business b = this.businessDao.get(acceptNo);
		b.setProcesses(convertListToMap(processDao.query(acceptNo)));//之前一直没有给b设定processes属性，在这里调用一个方法，给b设定上；
		return b;                     //注意，这个方法调用时，里边传递的参数为List类型的，即为通过query查找到的List集合
	}

	public Map<String,BusinessProcess> convertListToMap(List<BusinessProcess> bps){//注：因为在business对象中，他的processes属性时Map类型的，所以这个方法的返回值也为Map类型的
		Map<String,BusinessProcess> mbps = new HashMap<String, BusinessProcess>();
		for (BusinessProcess bp : bps) {//遍历根据acceptNo查到的所有的processes(由调用方法传入值)，分别将每个值赋给HashMap类型的对象，进行存储，得到对应的Map，以bpId作为key
			mbps.put(bp.getId(), bp);
		}return mbps;//这个方法将从数据库中得到的list类型的processes，转换为了Map类型的，以便于设定到b对象中，之所以选择Map类型的，是因为，类型少，用Map类遍历查询更快
	}
	
	public void finishCheck(String acceptNo, String suggestion) {
		super.finish(acceptNo, "07", "C",suggestion);
		
	}

	public void backCheck(String acceptNo, String suggestion) {
		super.finish(acceptNo, "06", "C",suggestion);
		
	}
//领导核查
	public List<Business> searchApprove(String acceptNo, User currentUser) {
		return businessDao.query(acceptNo, currentUser,"C","07","08");
	}

	public void finishApprove(String acceptNo, String suggestion) {
		super.finish(acceptNo, "10", "D",suggestion);
		
	}

	public void backApprove(String acceptNo, String suggestion) {
		super.finish(acceptNo, "09", "D",suggestion);
		
	}

	public void releaseApprove(String acceptNo) {
		super.release(acceptNo, "07", "C");
		
	}

	public void lockApprove(String acceptNo, User currentUser) {
		super.lock(acceptNo, currentUser, "08", "D");
	}




}
