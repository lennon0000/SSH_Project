package cn.com.leadfar.oa.model;

public class Dept extends Party {
	private int code;
	private int phoneNumber;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String getPrincipalType() {
		
		return "Dept";
	}
}
