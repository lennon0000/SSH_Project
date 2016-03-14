package cn.com.leadfar.oa.dao;

import java.util.List;
import java.util.Set;

import cn.com.leadfar.oa.action.RoleAction;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.model.UserRoles;
import cn.com.leadfar.oa.partyVO.PagerVO;


public interface UserRolesDao extends BaseDao {

	List<UserRoles> getRoles(User user);

	

}
