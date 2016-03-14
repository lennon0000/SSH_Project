package cn.com.leadfar.oa.service;

import java.util.List;

import cn.com.leadfar.oa.model.ActionResource;
import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.partyVO.MenuVO;

public interface MenuService {

	List<Menu> listMenu();

	void save(Menu menu);

	Menu getMenuById(int id);

	void update(Menu menu);

	void del(Menu menu);

	List<Menu> getMenuList(Menu menuById);

	List<Integer> getAllMenuIdS();

	List<Menu> getTopMenuList();


	
}
