package cn.com.leadfar.oa.action;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.annotations.Oper;
import cn.com.leadfar.oa.annotations.Res;
import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Position;
import cn.com.leadfar.oa.partyVO.PartyVO;
import cn.com.leadfar.oa.service.CompanyService;
import cn.com.leadfar.oa.service.PartyService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

@Component
@Scope("prototype")
@Res(name="组织机构操作",sn="party",orderNumber=1)
public class PartyAction implements ModelDriven {
	@Resource
	private PartyService partyService;
	@Resource
	private CompanyService companyService;
	protected Party party;
	private String sSearch;

	@Override
	public Object getModel() {
		if (party == null) {
			party = new Party();
		}
		return party;
	}
	@Oper
	public String list() {

		return "party_list";
	}
	@Oper
	public String listPersons() {
		return "persons_list";
	}
	@Oper
	public String updateInput() {
		party = (Party) partyService.getParty(party.getId());
		return "update_input";
	}
	@Oper
	public String update() {
		partyService.update(party);
		return "update_success";
	}
	@Oper
	public String addInput() {

		return "add_input";
	}
	@Oper
	public String add() {
		partyService.save(party);
		return "update_success";
	}
	@Oper
	public String del() {
		try {
			partyService.del(partyService.getParty(party.getId()));
		} catch (RuntimeException re) {
			ActionContext.getContext().put("delInfor", re.getMessage());// TODO:可以改为跳转到其他的地址，然后服务器重定向，跳转到updateInput方法
			party = (Position) partyService.getParty(party.getId());
			return "update_input";
		}

		return "update_success";
	}

	public void tree() throws IOException {
		Party p = companyService.getCompany(0);
		PartyVO vo = new PartyVO(companyService.getCompany(0));
		try {
			String jsonString = JSONMapper.toJSON(vo).render(true);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(jsonString);

		} catch (MapperException e) {
			e.printStackTrace();
		}
	}

	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
		
	}
}