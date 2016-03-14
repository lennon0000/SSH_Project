package cn.com.leadfar.oa.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.annotations.Oper;
import cn.com.leadfar.oa.annotations.Res;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.partyVO.PagerVO;
import cn.com.leadfar.oa.service.PartyService;
import cn.com.leadfar.oa.utils.SystemContext;

import com.sdicons.json.mapper.JSONMapper;

@Component
@Scope("prototype")
@Res(name="人员操作",sn="person",orderNumber=5,parentSn="party")
public class PersonAction extends PartyAction {
	@Resource
	private PartyService partyService;
	private String sSearch;
	private int parentId;

	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	@Override
	public Object getModel() {
		if (party == null) {
			party = new Person();
		}
		return party;
	}

	@Oper
	public String del() {
		partyService.del(partyService.getParty(party.getId()));

		return "update_success";
	}

	public void listPerson() {

		// 分页查询后台
		// 将parentId和sSearch两个查询条件传递
		PagerVO pagerVOs = new PagerVO();
		partyService.findPersons(pagerVOs, parentId, sSearch);
		
		PagerVO pv2 = new PagerVO();
		pv2.setTotal(pagerVOs.getTotal());
		
		int total = SystemContext.getOffset() + SystemContext.getPagesize();
		if(total>pagerVOs.getTotal()){
			total = pagerVOs.getTotal();
		}
		
		pv2.setDatas(pagerVOs.getDatas().subList(SystemContext.getOffset(),
				total));
		// 页面中需要展现的数据为二维数组，不是person list，数组包括以下三个属性
		Map vo = new HashMap();// 将pagerVO对象，转换为ajaxjason需要的对象格式
		vo.put("iTotalRecords", pv2.getTotal());
		vo.put("iTotalDisplayRecords", pv2.getTotal());
		vo.put("aaData", pv2.getDatas());

		// 因为用ajaxjason获得数据进行输出及控制时，操作的是JSON格式的数据，所以得把数据转换为JSON格式
		try {// 将vo对象转换为JSON数据格式
			String jsonString = JSONMapper.toJSON(vo).render(false);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getParentId() {
		return parentId;
	}

}
