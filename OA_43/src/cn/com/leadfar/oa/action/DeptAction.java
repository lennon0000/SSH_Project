package cn.com.leadfar.oa.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.annotations.Res;
import cn.com.leadfar.oa.model.Dept;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.service.PartyService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope("prototype")
@Res(name="部门操作",sn="dept",orderNumber=3,parentSn="party")
public class DeptAction extends PartyAction {
	@Override
	public Object getModel() {
		if(party==null){
			party=new Dept();
		}
		return party;
	}
	
}
