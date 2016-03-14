package cn.com.leadfar.oa.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.partyVO.LoginUserVO;
import cn.com.leadfar.oa.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

@Component
@Scope("prototype")

public class LoginAction implements ModelDriven {

	@Resource
	private UserService userService;
	private User user;
	@Override
	public Object getModel() {
		if(user ==null){
		user = new User();	
		}
		return user;
	}
	public String login(){//根据用户名密码，首先查找是否存在，再是将所有的menu都查找出来，再查找该用户的权限，根据他的权限，将没有权限的menu删除，显示其他的
		user = userService.getUserByNamePassword(user.getUserName(),user.getPassword());
		
		if(user==null){//如果用户不存在的话，抛出信息到登录界面
			
			ActionContext.getContext().put("loginInfor", ("用户名或登录密码错误，请重新登录"));
			return "login_unsuccess";
		}
		//获得user后将用户的信息，放入session中，从页面获得
		LoginUserVO userInfor = new LoginUserVO();
		userInfor.setId(user.getId());
		userInfor.setIp(ServletActionContext.getRequest().getRemoteHost());
		userInfor.setUserName(user.getUserName());
		userInfor.setName(user.getPerson().getName());
		userInfor.setLoginTime(new Date());
		ServletActionContext.getRequest().getSession().setAttribute("infor", userInfor);
		return "login_success";
	}
	
}
