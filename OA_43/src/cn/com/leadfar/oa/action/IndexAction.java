package cn.com.leadfar.oa.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.partyVO.AuthMenuVO;
import cn.com.leadfar.oa.partyVO.LoginUserVO;
import cn.com.leadfar.oa.service.AclService;
import cn.com.leadfar.oa.service.MenuService;
import cn.com.leadfar.oa.utils.JSONUtils;

@Component
@Scope("prototype")
public class IndexAction extends BaseAction {
	
	@Resource
	private MenuService menuService;
	@Resource
	private AclService aclService;
	
	public void authMenuTree(){
		LoginUserVO loginUser = getCurrentUser();
		//先获得该用户所有的授权的menu
		List<Menu> menus = aclService.getAllPermitMenu(loginUser.getId());
		
		//然后利用menuVO里边的方法将授权了的menu  List转换为menuVO list
		List<AuthMenuVO> menuVOs = new ArrayList<AuthMenuVO>();
		for (Menu menu : menus) {
			AuthMenuVO vo = new AuthMenuVO(menu);
			menuVOs.add(vo);//不用之前的menuVO，之前的得遍历他的子类，所以不用，两个VO中，针对每个menuVO生成的属性相同，就是生成方法不同
		}
		
		//然后转换为jason类型
		JSONUtils.toJSON(menuVOs);
	}

}
