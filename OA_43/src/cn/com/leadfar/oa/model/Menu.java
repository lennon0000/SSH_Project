package cn.com.leadfar.oa.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Menu implements SysResource {
	private int id;
	private String name;
	private String href;
	private int orderNumber;
	private int sn;
	private Menu parent;
	private Set<Menu> children;
	
	public int getResourceId(){
	return  id;	
	}
	public int[] getOpersIndex(){
		return new int[]{0};
	}
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
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	public Menu getParent() {
		return parent;
	}
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	public Set<Menu> getChildren() {
		return children;
	}
	public void setChildren(Set<Menu> children) {
		this.children = children;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getHref() {
		return href;
	}
	@Override
	public String getResourceType() {
		return "Menu";
	}

	@Override
	public List<SysResource> getChildrenResource() {
		if(children != null){
			List<SysResource> cs = new ArrayList<SysResource>();
			cs.addAll(children);
			return cs;
		}
		return null;
	}
}
