package cn.com.leadfar.oa.dao.impl;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.dao.RoleDao;
import cn.com.leadfar.oa.dao.UserRolesDao;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.model.UserRoles;
import cn.com.leadfar.oa.partyVO.PagerVO;

@Component
public class UserRolesDaoImpl extends BaseDaoImpl implements UserRolesDao {

	@Override
	public List<UserRoles> getRoles(User user) {
		return getSession().createQuery("from UserRoles ur where ur.user.id = ?").setParameter(0, user.getId()).list();
	}


}
