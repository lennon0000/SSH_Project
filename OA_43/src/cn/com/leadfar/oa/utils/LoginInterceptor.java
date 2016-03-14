package cn.com.leadfar.oa.utils;

import org.apache.struts2.ServletActionContext;

import cn.com.leadfar.oa.partyVO.LoginUserVO;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//获得当前用户登录信息
		LoginUserVO vo = (LoginUserVO)ServletActionContext.getRequest().getSession().getAttribute("infor");
		if(vo==null){//如果用户为null的话，则没有登录
			return "login_unsuccess";
		}
		
		//继续向下执行
		return invocation.invoke();

	}
	

}
