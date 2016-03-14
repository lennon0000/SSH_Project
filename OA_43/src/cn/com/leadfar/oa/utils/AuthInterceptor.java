package cn.com.leadfar.oa.utils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;


import cn.com.leadfar.oa.model.ActionResource;
import cn.com.leadfar.oa.partyVO.LoginUserVO;
import cn.com.leadfar.oa.service.AclService;
import cn.com.leadfar.oa.service.ResourceService;
import cn.com.leadfar.oa.utils.SystemContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {

	@Resource
	private AclService aclService;
	
	@Resource
	private ResourceService resourceService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		//要调用的Action类名
		String className = invocation.getProxy().getAction()
			.getClass().getName();
		
		//根据要调用的类名来查找ActionResource对象
		ActionResource actionResource = resourceService.findActionResourceByClassName(className);
		
		//如果这个Action类并不是一种资源，表示它无需受到权限控制，因此直接往下运行
		if(actionResource == null){
			return invocation.invoke();
		}
		
		//得到要调用的方法名
		String methodName = invocation.getProxy().getMethod();
		
		//根据要调用的方法名，得到其对应的操作标识
		String operSn = actionResource.getOperSnByMethodName(methodName);
		
		//如果这个方法并没有被定义为这个资源的一种操作，那么即意味着本方法无需进行权限控制！
		if(operSn == null){
			return invocation.invoke();
		}
		
		//得到当前登录用户的ID
		int userId = ((LoginUserVO)ServletActionContext.getRequest().getSession().getAttribute("infor")).getId();
		
		//是否允许当前登录用户执行本资源的本操作？
		boolean permit = aclService.operHasPermit(userId, actionResource.getSn(), operSn);
		
		//如果允许，则继续往下执行
		if(permit){
			return invocation.invoke();
		}
		
		throw new RuntimeException("您无权执行本操作【resourceSn="+actionResource.getSn()+",operSn="+operSn+"】，请联系系统管理员！");
	}

}
