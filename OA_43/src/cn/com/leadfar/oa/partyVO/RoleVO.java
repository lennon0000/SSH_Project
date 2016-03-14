package cn.com.leadfar.oa.partyVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.leadfar.oa.model.Role;

public class RoleVO {
	private String data;
	private Map attr = new HashMap();
	public Map getAttr() {
		return attr;
	}

	public void setAttr(Map attr) {
		this.attr = attr;
	}

	private List<RoleVO> children;
	
	@SuppressWarnings("unchecked")
	public RoleVO(Role role) {
		this.data = role.getName();//TODO:为什么加this，效果？
		
		this.attr.put("type", "Role");
		this.attr.put("id", role.getId());
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<RoleVO> getChildren() {
		return children;
	}

	public void setChildren(List<RoleVO> children) {
		this.children = children;
	}



	
}
