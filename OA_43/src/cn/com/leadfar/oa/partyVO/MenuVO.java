package cn.com.leadfar.oa.partyVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.model.Party;

public class MenuVO {
	private String data;
	private Map attr = new HashMap();
	private List<MenuVO> children;
	
	public Map getAttr() {
		return attr;
	}

	public void setAttr(Map attr) {
		this.attr = attr;
	}

	

	public MenuVO(Menu menu) {

		this.data = menu.getName();// TODO:为什么加this，效果？

		this.attr.put("id", menu.getId());
		this.attr.put("href", menu.getHref());
		@SuppressWarnings("unused")
		Set<Menu> cp = menu.getChildren();
		if (!menu.getChildren().isEmpty()) {
			children = new ArrayList<MenuVO>();
			for (Menu child : menu.getChildren()) {
					children.add(new MenuVO(child));

			}

		}
	}

	public MenuVO() {
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<MenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuVO> children) {
		this.children = children;
	}

}
