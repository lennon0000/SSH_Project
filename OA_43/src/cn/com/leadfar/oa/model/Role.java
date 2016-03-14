package cn.com.leadfar.oa.model;

import java.util.List;
import java.util.Set;

public class Role implements Principal{
	private int id;
	private String name;
	private String status;
	private Set<UserRoles> users; 
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
	public void setUsers(Set<UserRoles> users) {
		this.users = users;
	}
	public Set<UserRoles> getUsers() {
		return users;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	@Override
	public String getPrincipalType() {
		
		return "Role";
	}
	@Override
	public int getPrincipalId() {
		return id;
	}
	@Override
	public List<Principal> getParentPrincipal() {
		return null;
	}
}
