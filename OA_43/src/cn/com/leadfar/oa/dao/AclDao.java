package cn.com.leadfar.oa.dao;

import java.util.List;

import cn.com.leadfar.oa.model.ACL;
import cn.com.leadfar.oa.model.Principal;
import cn.com.leadfar.oa.model.SysResource;


public interface AclDao extends BaseDao {

	void delAcls(String principalType, int principalId, String resourceType);

	ACL getAcl(String principalType, int principalId, String resourceType, int resourceId);

	List<ACL> getAcls(String principalType, int principalId, String type);

	List<SysResource> getAllSysResources(String resourceType);

	Principal getPrincipal(String principalType, int principalId);

	List<Integer> getOperIds(String resourceType, int resourceId);


}
