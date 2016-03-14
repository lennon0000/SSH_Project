package cn.com.leadfar.oa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.annotations.Oper;
import cn.com.leadfar.oa.annotations.Res;
import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.partyVO.MenuVO;
import cn.com.leadfar.oa.service.MenuService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

@Component
@Scope("prototype")
@Res(name="菜单操作",sn="menu",orderNumber=9,parentSn="security")
public class MenuAction implements ModelDriven {
	private Menu menu;
	private int userId;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	@Resource
	private MenuService menuService;

	@Override
	public Object getModel() {
		if (menu == null) {
			menu = new Menu();
		}
		return menu;
	}

	
	 
	public void tree() throws IOException {
		List<Menu> rcs = menuService.getTopMenuList();
		List<MenuVO> mvos = new ArrayList<MenuVO>();
		
		for (Menu rc : rcs) {
			MenuVO vo = new MenuVO(rc);// 应该循环这个语句
			mvos.add(vo);
		}
		
		String jsonString  = null;
		try {
				jsonString = JSONMapper.toJSON(mvos).render(true);
			
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(jsonString);
			
		} catch (MapperException e) {
			e.printStackTrace();
		}

	}
	@Oper
	public String listMenu() {
		return "menu_list";
	}
	@Oper
	public String del() {
		try {
			menuService.del(menu);
		} catch (RuntimeException re) {
			ActionContext.getContext().put("delInfor", re.getMessage());// TODO:可以改为跳转到其他的地址，然后服务器重定向，跳转到updateInput方法
			menu = menuService.getMenuById(menu.getId());
			return "update_input";
		}

		return "update_success";
	}
	@Oper
	public String addInput() {

		return "add_input";
	}
	@Oper
	public String updateInput() {
		menu = menuService.getMenuById(menu.getId());
		return "update_input";
	}
	@Oper
	public String add() {
		menuService.save(menu);
		return "update_success";
	}
	@Oper
	public String update() {
		menuService.update(menu);
		return "update_success";
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserId() {
		return userId;
	}

}
