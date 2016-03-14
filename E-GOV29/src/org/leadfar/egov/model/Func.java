package org.leadfar.egov.model;

import java.util.List;

public class Func {
	private String id;
	private String name;
	private String url;
	private String pid;
	private Func parent;
	private String checked;
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public Func getParent() {
		return parent;
	}
	public void setParent(Func parent) {
		this.parent = parent;
	}
	public List<Func> getChildren() {
		return children;
	}
	public void setChildren(List<Func> children) {
		this.children = children;
	}
	private List<Func> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
}