package cn.com.leadfar.oa.service;

import java.util.List;

import cn.com.leadfar.oa.action.RoleAction;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.partyVO.PagerVO;

public interface RoleService {

	void listRoles(PagerVO pagerVOs,String sSearch);

	void save(Role role);

	Role getRoleById(int id);

	void update(Role role);

	void del(Role role);

	List<Role> getAllRoles();
	
}
