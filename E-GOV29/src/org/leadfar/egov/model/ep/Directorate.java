
package org.leadfar.egov.model.ep;

/**
 * 董事会成员
 * @author wangbo
 *
 */
public class Directorate {

	private int id;
	private String name;//姓名
	private String cardNo;//身份证号
	private String produceMode;//产生方式
	private String duty;//职务
	
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

	public String getProduceMode() {
		return produceMode;
	}

	public void setProduceMode(String produceMode) {
		this.produceMode = produceMode;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public int getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(int enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	
}
