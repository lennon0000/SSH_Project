package cn.com.leadfar.oa.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.annotations.Oper;
import cn.com.leadfar.oa.annotations.Res;
import cn.com.leadfar.oa.model.Company;
import cn.com.leadfar.oa.service.CompanyService;

@Component
@Scope("prototype")
@Res(name="公司操作",sn="company",orderNumber=2,parentSn="party")
public class CompanyAction extends PartyAction {

	private int companyId;
	@Resource
	private CompanyService companyService;
	@Oper(name="公司信息维护",index=4,sn="saveCompany")
	public String list() {// TODO:应该从数据库中先查找出来，
		party = companyService.getCompany(companyId);// 页面传id，则为子公司，不传，则id为0，为总公司
		return "company_list";
	}
	@Oper(name="公司信息维护",index=4,sn="saveCompany")
	public String save() {// TODO:保存前在service中要判断是更新还是保存
		companyService.save(party);// 注意，这只是从页面提交的数据，不能直接更新这个company对象，他的关联关系会消失，得根据id取得

		return "update_success";
	}


	@Override
	public Object getModel() {
		if (party == null) {
			party = new Company();
		}
		return party;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getCompanyId() {
		return companyId;
	}



}
