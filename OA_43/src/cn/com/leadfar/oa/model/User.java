package cn.com.leadfar.oa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class User implements Principal{
	private int id;
	private String userName;
	private String password;
	private Person person;
	private Set<UserRoles> roles; //TODO:这里命名不当，应该为userRoles
	private Set<LeaveForm> leaveForms;
	private Set<UserLeaveForm> userLeaveForms;
	
	public Set<LeaveForm> getLeaveForms() {
		return leaveForms;
	}
	public void setLeaveForms(Set<LeaveForm> leaveForms) {
		this.leaveForms = leaveForms;
	}
	public Set<UserLeaveForm> getUserLeaveForms() {
		return userLeaveForms;
	}
	public void setUserLeaveForms(Set<UserLeaveForm> userLeaveForms) {
		this.userLeaveForms = userLeaveForms;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Set<UserRoles> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRoles> roles) {
		this.roles = roles;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Person getPerson() {
		return person;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	@Override
	public String getPrincipalType() {
		
		return "User";
	}
	@Override
	public int getPrincipalId() {
		
		return id;
	}
	@Override
	public List<Principal> getParentPrincipal() {
		List<Principal> parents = new ArrayList<Principal>();
		Principal parent = person.getParent();//将该用户对应的person的父类，加入到parents中
		parents.add(parent);
		if(roles!=null){//当有角色时，将该用户的角色添加进去
			for (UserRoles role : roles) {
				parents.add(role.getRole());
			}
			
		}
		
		return parents;
	}
}
