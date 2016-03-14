package cn.com.leadfar.oa.partyVO;

import java.util.ArrayList;
import java.util.List;

public class PagerVO {
	private int total;
	private List datas = new ArrayList();
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List getDatas() {
		return datas;
	}
	public void setDatas(List datas) {
		this.datas = datas;
	}
	public void addPagerVO(PagerVO pagerVOs){
		this.total +=pagerVOs.getTotal();
		this.datas.addAll(pagerVOs.getDatas());
	}
	
}
