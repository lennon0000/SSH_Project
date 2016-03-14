package cn.com.leadfar.oa.model;

public class Person extends Party {
	private String sex;
	private String email;
	private String duty;
	private int phoneNumber;
	private int code;
	private int qq;
	private int msn;
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public int getQq() {
		return qq;
	}
	public void setQq(int qq) {
		this.qq = qq;
	}
	public int getMsn() {
		return msn;
	}
	public void setMsn(int msn) {
		this.msn = msn;
	}
	@Override
	public String getPrincipalType() {
		
		return "Person";
	}
}
