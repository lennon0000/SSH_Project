package cn.com.leadfar.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.leadfar.oa.dao.RoleDao;
import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.partyVO.PagerVO;
import cn.com.leadfar.oa.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {
	@Resource
	private RoleDao roleDao;

	@Override
	public void save(Role role) {
		roleDao.save(role);

	}

	@Override
	public void listRoles(PagerVO pagerVOs, String sSearch) {
		if (sSearch == null) {
			pagerVOs.addPagerVO(roleDao.getAllRoles());
		} else {
			pagerVOs.addPagerVO(roleDao.getAllRoles("%" + sSearch + "%"));
		}
	}

	@Override
	public Role getRoleById(int id) {
		
		return roleDao.getRoleById(id);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
		
	}

	@Override
	public void del(Role role) {
		roleDao.del(role);
		
	}

	@Override
	public List<Role> getAllRoles() {
		
		return roleDao.getAll(Role.class);
	}


}
