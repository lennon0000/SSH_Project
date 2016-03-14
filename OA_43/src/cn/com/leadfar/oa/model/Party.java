package cn.com.leadfar.oa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Party implements Principal{
	protected Integer id;//TODO:注意，这里id为interger类型，和action中的companyId类型不同
	protected String name;
	protected String description;//TODO:设置为protected则他的子类都可以访问这一属性，通过company.party.id
	protected Party parent;
	protected Set<Party> children;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Set<Party> getChildren() {
		return children;
	}
	public void setChildren(Set<Party> children) {
		this.children = children;
	}
	public void setParent(Party parent) {
		this.parent = parent;
	}
	public Party getParent() {
		return parent;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	@Override
	public String getPrincipalType() {
		
		return "Party";
	}
	@Override
	public int getPrincipalId() {
		
		return id;
	}
	@Override
	public List<Principal> getParentPrincipal() {
		List<Principal> p = new ArrayList<Principal>();
		if(parent!=null){
			p.add(parent);
		}
		
		return p;
	}
}
