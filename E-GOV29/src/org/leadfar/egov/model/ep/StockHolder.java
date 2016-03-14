package org.leadfar.egov.model.ep;


public class StockHolder {
	
	private int id;
	private String name; //股东名称
	private String cardNo; //证照号码
	private float moneyInvested; //出资额
	private float percentage; //出资比例
	private int enterpriseId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public float getMoneyInvested() {
		return moneyInvested;
	}
	public void setMoneyInvested(float moneyInvested) {
		this.moneyInvested = moneyInvested;
	}
	public float getPercentage() {
		return percentage;
	}
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	public int getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
	
}
