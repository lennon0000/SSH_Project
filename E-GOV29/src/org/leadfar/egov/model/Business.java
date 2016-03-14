package org.leadfar.egov.model;

import java.util.Date;
import java.util.Map;

public class Business {
	private String acceptNo;
	private String enterpriseName;
	private Date createTime;
	
	private Dic adm;
	
	private Dic businessType;
	
	private Dic processStatus;
	
	private Relation relation;
	
	private Map<String, BusinessProcess> processes;

	public Map<String, BusinessProcess> getProcesses() {
		return processes;
	}

	public void setProcesses(Map<String, BusinessProcess> processes) {
		this.processes = processes;
	}

	public String getAcceptNo() {
		return acceptNo;
	}

	public void setAcceptNo(String accessNo) {
		this.acceptNo = accessNo;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Dic getAdm() {
		return adm;
	}

	public void setAdm(Dic adm) {
		this.adm = adm;
	}

	public Dic getBusinessType() {
		return businessType;
	}

	public void setBusinessType(Dic businessType) {
		this.businessType = businessType;
	}

	public Dic getProcessStatus() {
		return processStatus;
	}

	public void setProcessStatus(Dic processStatus) {
		this.processStatus = processStatus;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}
}
