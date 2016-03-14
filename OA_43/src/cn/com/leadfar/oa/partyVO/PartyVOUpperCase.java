package cn.com.leadfar.oa.partyVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.leadfar.oa.model.Party;

public class PartyVOUpperCase {
	private String data;
	private Map attr = new HashMap();
	public Map getAttr() {
		return attr;
	}

	public void setAttr(Map attr) {
		this.attr = attr;
	}

	private List<PartyVOUpperCase> children;
	
	@SuppressWarnings("unchecked")
	public PartyVOUpperCase(Party party) {
		this.data = party.getName();//TODO:为什么加this，效果？
		System.out.println(party.getClass().getSimpleName().toUpperCase());
		this.attr.put("objectType", party.getClass().getSimpleName());
		this.attr.put("id", party.getId());
		@SuppressWarnings("unused")
		Set<Party> cp = party.getChildren();
		if(!party.getChildren().isEmpty()){
			children = new ArrayList<PartyVOUpperCase>();
			for (Party  child: party.getChildren()) {
				if (!child.getClass().getName().endsWith("Person")) {//TODO:这里是通过加限定条件来限定菜单栏没有person，如何改为用filter的
					children.add(new PartyVOUpperCase(child));
				}
				
			}
			
		}
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<PartyVOUpperCase> getChildren() {
		return children;
	}

	public void setChildren(List<PartyVOUpperCase> children) {
		this.children = children;
	}



	
}
