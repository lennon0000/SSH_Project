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
		// ���Ǵ�ҳ���ȡ�ģ���ߵ��Ǵ�BusinessDao�����ɵ�
		BusinessProcess bp = new BusinessProcess();
		bp.setId(b.getAcceptNo() + "_A");
		bp.setStep("A");
		bp.setStartTime(new Date());
		bp.setUser(user);
		processDao.save(bp);

	}

	//��Ϣ¼��

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
	//����Ǽ�

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

//	�������
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
		b.setProcesses(convertListToMap(processDao.query(acceptNo)));//֮ǰһֱû�и�b�趨processes���ԣ����������һ����������b�趨�ϣ�
		return b;                     //ע�⣬�����������ʱ����ߴ��ݵĲ���ΪList���͵ģ���Ϊͨ��query���ҵ���List����
	}

	public Map<String,BusinessProcess> convertListToMap(List<BusinessProcess> bps){//ע����Ϊ��business�����У�����processes����ʱMap���͵ģ�������������ķ���ֵҲΪMap���͵�
		Map<String,BusinessProcess> mbps = new HashMap<String, BusinessProcess>();
		for (BusinessProcess bp : bps) {//��������acceptNo�鵽�����е�processes(�ɵ��÷�������ֵ)���ֱ�ÿ��ֵ����HashMap���͵Ķ��󣬽��д洢���õ���Ӧ��Map����bpId��Ϊkey
			mbps.put(bp.getId(), bp);
		}return mbps;//��������������ݿ��еõ���list���͵�processes��ת��Ϊ��Map���͵ģ��Ա����趨��b�����У�֮����ѡ��Map���͵ģ�����Ϊ�������٣���Map�������ѯ����
	}
	
	public void finishCheck(String acceptNo, String suggestion) {
		super.finish(acceptNo, "07", "C",suggestion);
		
	}

	public void backCheck(String acceptNo, String suggestion) {
		super.finish(acceptNo, "06", "C",suggestion);
		
	}
//�쵼�˲�
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
