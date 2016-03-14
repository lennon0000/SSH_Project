package cn.com.leadfar.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.dao.RoleDao;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.partyVO.PagerVO;

@Component
public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

	@SuppressWarnings("unchecked")
	@Override
	public PagerVO getAllRoles() {
		PagerVO pv = new PagerVO();
		List datas = getSession()
				.createQuery("select r.id,r.name from Role r ").list();
		pv.setDatas(datas);
		pv.setTotal(datas.size());
		return pv;

	}

	@Override
	public PagerVO getAllRoles(String sSearch) {
		PagerVO pv = new PagerVO();
		List datas = getSession()
				.createQuery(
						"select r.id,r.name from Role r where r.name like ? ")
				.setParameter(0, sSearch).list();
		pv.setDatas(datas);
		pv.setTotal(datas.size());
		return pv;
	}

	@Override
	public Role getRoleById(int id) {
		return (Role) getSession().createQuery("from Role r where r.id = ?").setParameter(0, id).uniqueResult();
	}

}
