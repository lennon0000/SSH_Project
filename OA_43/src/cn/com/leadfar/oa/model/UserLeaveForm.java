package cn.com.leadfar.oa.model;

import java.util.Date;


public class UserLeaveForm {
	private int id;
	private String content;
	private Date time;//审批时间
	private LeaveForm leaveForm;
	private User checker;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public LeaveForm getLeaveForm() {
		return leaveForm;
	}
	public void setLeaveForm(LeaveForm leaveForm) {
		this.leaveForm = leaveForm;
	}
	public User getChecker() {
		return checker;
	}
	public void setChecker(User checker) {
		this.checker = checker;
	}
	
}
