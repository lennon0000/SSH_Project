package cn.com.leadfar.oa.dao;

import java.util.List;

import cn.com.leadfar.oa.action.RoleAction;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.partyVO.PagerVO;


public interface RoleDao extends BaseDao {

	PagerVO getAllRoles(String sSearch);

	PagerVO getAllRoles();

	Role getRoleById(int id);

}
