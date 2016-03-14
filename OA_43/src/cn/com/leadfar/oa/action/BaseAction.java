package cn.com.leadfar.oa.action;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.partyVO.LoginUserVO;
import cn.com.leadfar.oa.service.AclService;

@Component
@Scope("prototype")

public class BaseAction  {
	@Resource
	private AclService aclService;
	
	public LoginUserVO getCurrentUser(){
		return (LoginUserVO) ServletActionContext.getRequest().getSession().getAttribute("infor");
	}
	public boolean permit(String resourceSn,String oper){
		//这里实现的是在jsp中控制button的展现，原理是针对这个用户，查询该资源的该操作(所有的资源，在t_resource表中)
		try {
			return aclService.operHasPermit(getCurrentUser().getId(),resourceSn,oper);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
