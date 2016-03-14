package org.leadfar.egov.model;

import java.util.List;

public class User {
	
	private int id;
	private String username;
	private Dic adm;//需要注意这里他的类型，因为他是参考的Dic里的admcode，可以得到对应的中文的名称,
	private String password;
	private String status;
	private String name;
	private String dept;
	private String ip;
	private List<Func> funcs;//需要注意，这个容易遗漏
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Dic getAdm() {
		return adm;
	}
	public void setAdm(Dic adm) {
		this.adm = adm;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public List<Func> getFuncs() {
		return funcs;
	}
	public void setFuncs(List<Func> funcs) {
		this.funcs = funcs;
	}
}
