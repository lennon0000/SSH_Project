package cn.com.leadfar.oa.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.leadfar.oa.dao.MenuDao;
import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Resource
	private MenuDao menuDao;

	@Override
	public List<Menu> listMenu() {

		return menuDao.getAllMenu();
	}

	@Override
	public void save(Menu menu) {
		menuDao.save(menu);

	}

	@Override
	public Menu getMenuById(int id) {
		if (id == 0) {//之前在action中为了查询所有的一级菜单项，需要查出root菜单，得有id=0这种情况，现在没有这种情况
			// TODO:则为查rootMenu，则换其他查询逻辑
			Menu rootMenu = new Menu();
			rootMenu = menuDao.getRootMenu();
			return rootMenu;
		} else {
			return menuDao.getMenuById(id);
		}

	}

	@Override
	public void update(Menu menu) {
		menuDao.update(menu);

	}

	@Override
	public void del(Menu menu) {
		List<Menu> children = menuDao.delCheck(menu);
		if (children.size()==0) {
			menuDao.del(menu);
		} else {
			throw new RuntimeException("有子节点，不能进行删除操作，请先删除子节点");
		}

	}

	@Override
	public List<Menu> getMenuList(Menu menuById) {
		
		
	
		return menuDao.delCheck(menuById);// 通过pid获得root的所有子类，不包括孙类
	}

	@Override
	public List<Integer> getAllMenuIdS() {
		return menuDao.getAllMenuIds();
	}

	@Override
	public List<Menu> getTopMenuList() {
		return menuDao.getTopMenuList();
	}


}
