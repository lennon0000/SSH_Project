package cn.com.leadfar.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.dao.RoleDao;
import cn.com.leadfar.oa.dao.UserDao;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.partyVO.PagerVO;

@Component
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	@Override
	public PagerVO getAllUsers(String sSearch) {
		PagerVO pv = new PagerVO();
		List datas = getSession()
				.createQuery(
						"select p.id,p.name,p.parent.name,u.userName from Person p left join p.user u where p.name like ? ")
				.setParameter(0, sSearch).list();
		pv.setDatas(datas);
		pv.setTotal(datas.size());
		return pv;

	}

	@Override
	public PagerVO getAllUsers() {
		
		PagerVO pv = new PagerVO();
		List datas = getSession()
				.createQuery("select p.id,p.name,p.parent.name,u.userName from Person p left join p.user u ").list();
		pv.setDatas(datas);
		pv.setTotal(datas.size());
		return pv;
	}

	@Override
	public User getUserById(int id) {
		return (User) getSession().createQuery("from User u where u.id = ?").setParameter(0, id).uniqueResult();
	}

	@Override
	public void evict(Person person) {
		getSession().evict(person);
		
	}

	@Override
	public List getAllUsersOnly() {
			String hql = "select p.id,p.name from Person p join p.user u where p.name like ?";
			return getSession().createQuery(hql).list();
	}

	@Override
	public List getAllUsersOnly(String sSearch) {
		String hql = "select p.id,p.name from Person p join p.user u where p.name like ?";
		return getSession().createQuery(hql).setParameter(0, sSearch).list();
	}

	@Override
	public User getUserByNamePassword(String userName, String password) {
		
		return (User) getSession().createQuery("from User u where u.userName= ? and u.password = ?").setParameter(0, userName).setParameter(1, password).uniqueResult();
	}



}
