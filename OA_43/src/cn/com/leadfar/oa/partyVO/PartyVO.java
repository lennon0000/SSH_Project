package cn.com.leadfar.oa.partyVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.com.leadfar.oa.model.Party;

public class PartyVO {
	private String data;
	private Map attr = new HashMap();
	public Map getAttr() {
		return attr;
	}

	public void setAttr(Map attr) {
		this.attr = attr;
	}

	private List<PartyVO> children;
	
	@SuppressWarnings("unchecked")
	public PartyVO(Party party) {
		this.data = party.getName();//TODO:为什么加this，效果？
		System.out.println(party.getClass().getSimpleName().toUpperCase());
		this.attr.put("objectType", party.getClass().getSimpleName().toLowerCase());
		this.attr.put("id", party.getId());
		@SuppressWarnings("unused")
		Set<Party> cp = party.getChildren();
		if(!party.getChildren().isEmpty()){
			children = new ArrayList<PartyVO>();
			for (Party  child: party.getChildren()) {
				if (!child.getClass().getName().endsWith("Person")) {//TODO:这里是通过加限定条件来限定菜单栏没有person，如何改为用filter的
					children.add(new PartyVO(child));
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

	public List<PartyVO> getChildren() {
		return children;
	}

	public void setChildren(List<PartyVO> children) {
		this.children = children;
	}



	
}
