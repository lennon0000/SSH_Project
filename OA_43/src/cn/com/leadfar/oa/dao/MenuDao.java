package cn.com.leadfar.oa.dao;

import java.util.List;
import java.util.Set;

import cn.com.leadfar.oa.action.RoleAction;
import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.partyVO.PagerVO;


public interface MenuDao extends BaseDao {

	List<Menu> getAllMenu();

	Menu getMenuById(int id);

	Menu getRootMenu();

	List<Menu> delCheck(Menu menu);

	List<Integer> getAllMenuIds();

	List<Menu> getTopMenuList();




}
