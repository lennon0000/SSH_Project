package cn.com.leadfar.oa.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.dao.AclDao;
import cn.com.leadfar.oa.model.ACL;
import cn.com.leadfar.oa.model.ActionMethodOper;
import cn.com.leadfar.oa.model.ActionResource;
import cn.com.leadfar.oa.model.Principal;
import cn.com.leadfar.oa.model.SysResource;

@Component
public class AclDaoImpl extends BaseDaoImpl implements AclDao {

	@Override
	public void delAcls(String principalType, int principalId,
			String resourceType) {
		Iterator it = getSession()
				.createQuery(
						"select a from ACL a where a.principalType = ? "
								+ "and a.principalId = ? and a.resourceType = ?")
				.setParameter(0, principalType).setParameter(1, principalId)
				.setParameter(2, resourceType).iterate();
		while (it.hasNext()) {
			getSession().delete(it.next());
		}
	}

	@Override
	public ACL getAcl(String principalType, int principalId,
			String resourceType, int resourceId) {
		String sql = ("select a from ACL a where a.principalType = ? and a.principalId = ? and a.resourceType = ? and a.resourceId = ?");
		ACL acl = (ACL) getSession().createQuery(sql).setParameter(0, principalType)
				.setParameter(1, principalId).setParameter(2, resourceType)
				.setParameter(3, resourceId).uniqueResult();
		return acl;
	}

	@Override
	public List<ACL> getAcls(String principalType, int principalId, String type) {
		return getSession()
				.createQuery(
						"select a from ACL a where a.principalType = ?"
								+ " and a.principalId = ? and a.resourceType = ? ")
				.setParameter(0, principalType).setParameter(1, principalId)
				.setParameter(2, type).list();
	}

	@Override
	public List<SysResource> getAllSysResources(String resourceType) {
		return getSession().createQuery("from " + resourceType).list();
	}

	@Override
	public Principal getPrincipal(String principalType, int principalId) {
		String sql = ("from " + principalType + " p where p.id = " + principalId);
		return (Principal) getSession().createQuery(sql).uniqueResult();
	}

	@Override
	public List<Integer> getOperIds(String resourceType, int resourceId) {
		List<Integer> operIds = new ArrayList<Integer>();
		if (resourceType.equals("Menu")) {
			operIds.add(0);// 如果是menu的话，则他的操作只有查询操作，operId为0（数据库中有这是在页面中保存前设置的的）
			return operIds;
		}
		if (resourceId != 1) {
			String sql = ("from " + resourceType + " a where a.id = " + resourceId);
			ActionResource a = (ActionResource) getSession().createQuery(sql)
					.uniqueResult();

			for (ActionMethodOper oper : a.getOpers().values()) {
				operIds.add(oper.getOperIndex());
			}
		}

		return operIds;
	}

}
