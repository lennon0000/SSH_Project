package cn.com.leadfar.oa.model;

import java.util.Date;
import java.util.Set;


public class LeaveForm {
	private int id;
	private User leaver;//请假者,注意是user对象，不是他的名字name
	private String content;
	private Date beginTime;
	private Date endTime;
	private int days;
	private int state;
	
	private Set<UserLeaveForm> userLeaveForms;
	
	
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
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public void setUserLeaveForms(Set<UserLeaveForm> userLeaveForms) {
		this.userLeaveForms = userLeaveForms;
	}
	public Set<UserLeaveForm> getUserLeaveForms() {
		return userLeaveForms;
	}
	public void setLeaver(User leaver) {
		this.leaver = leaver;
	}
	public User getLeaver() {
		return leaver;
	}
}
