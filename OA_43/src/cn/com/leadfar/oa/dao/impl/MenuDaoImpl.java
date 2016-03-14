package cn.com.leadfar.oa.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.dao.MenuDao;
import cn.com.leadfar.oa.model.Menu;

@Component
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao {

	@Override
	public List<Menu> getAllMenu() {
		
		return getSession().createQuery("from Menu").list();
	}

	@Override
	public Menu getMenuById(int id) {
		
		return (Menu) getSession().createQuery("from Menu m where m.id = ?").setParameter(0, id).uniqueResult();
	}

	@Override
	public Menu getRootMenu() {
		return (Menu) getSession().createQuery("from Menu m where m.parent.id =null").uniqueResult();
		
	}

	@Override
	public List<Menu> delCheck(Menu menu) {
		int pid = menu.getId();
		return getSession().createQuery("from Menu m where m.parent.id = ? ")
				.setParameter(0, pid).list();
	}

	@Override
	public List<Integer> getAllMenuIds() {
//		int rootId = (Integer) getSession().createQuery("select m.id from Menu m where m.parent.id =null").uniqueResult();
//		return getSession().createQuery("select m.id from Menu m where m.parent.id = ?").setParameter(0, rootId).list();
		return getSession().createQuery("select m.id from Menu m where m.parent.id =null").list();
	}

	@Override
	public List<Menu> getTopMenuList() {
		return getSession().createQuery("from Menu m where m.parent.id =null").list();
	}




}
