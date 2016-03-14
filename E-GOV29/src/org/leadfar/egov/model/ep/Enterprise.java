/*
 * 企业基本信息
 */
package org.leadfar.egov.model.ep;
import java.util.Date;

import org.leadfar.egov.model.Dic;


public class Enterprise {

	private int id; 
	private String registerNo; //注册号    区划代码+企业类型+5位流水号 
	private String name;  //企业名称
	
	private Dic enterpriseStatus; //企业状态 
	private Dic adm; //区划代码,该企业注册的所在工商行政管理机关
	
	private Date foundDate;//成立日期

	private String operateRange;//经营范围
	private int operateLimited;//经营有效期 如允许20年
	private String products;//主要产品
	private int capital;//注册资本
	private String address;//注册地址
	private String operateAddress;//经营地址
	
	private Dic enterpriseType; //企业类型
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRegisterNo() {
		return registerNo;
	}
	public void setRegisterNo(String registerNo) {
		this.registerNo = registerNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public Dic getAdm() {
		return adm;
	}
	public void setAdm(Dic adm) {
		this.adm = adm;
	}
	public Date getFoundDate() {
		return foundDate;
	}
	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}
	
	public String getOperateRange() {
		return operateRange;
	}
	public void setOperateRange(String operateRange) {
		this.operateRange = operateRange;
	}
	public int getOperateLimited() {
		return operateLimited;
	}
	public void setOperateLimited(int operateLimited) {
		this.operateLimited = operateLimited;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public int getCapital() {
		return capital;
	}
	public void setCapital(int capital) {
		this.capital = capital;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getOperateAddress() {
		return operateAddress;
	}
	public void setOperateAddress(String operateAddress) {
		this.operateAddress = operateAddress;
	}
	public Dic getEnterpriseStatus() {
		return enterpriseStatus;
	}
	public void setEnterpriseStatus(Dic enterpriseStatus) {
		this.enterpriseStatus = enterpriseStatus;
	}
	public Dic getEnterpriseType() {
		return enterpriseType;
	}
	public void setEnterpriseType(Dic enterpriseType) {
		this.enterpriseType = enterpriseType;
	}
	
	
	
	
}