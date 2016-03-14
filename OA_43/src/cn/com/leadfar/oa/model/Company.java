package cn.com.leadfar.oa.model;

public class Company extends Party {
	private String address;
	private String website;
	private String email;
	private String trade;
	private int phoneNumber;
	private int faxNumber;
	private int postCode;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(int faxNumber) {
		this.faxNumber = faxNumber;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	@Override
	public String getPrincipalType() {

		return "Company";
	}
}
