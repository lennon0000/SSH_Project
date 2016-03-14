package cn.com.leadfar.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.com.leadfar.oa.dao.BaseDao;
import cn.com.leadfar.oa.dao.PartyDao;
import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.partyVO.PagerVO;

@Repository("partyDao")
public class PartyDaoImpl extends BaseDaoImpl implements PartyDao {

	@Override
	public List<Party> delCheck(Party party) {
		int pid = party.getId();
		return getSession().createQuery("from Party p where p.parent.id = ? ")
				.setParameter(0, pid).list();
	}

	@Override
	public List<Person> getPersons(Integer id) {
		return getSession().createQuery("from Person p where p.parent.id = ?")
				.setParameter(0, id).list();
	}

	@Override
	public List<Party> getChildren(Integer id) {
		return getSession()
				.createQuery(
						"from Party p where p.parent.id = ? and p.name != Person")
				.setParameter(0, id).list();
	}

	
	@Override
	public PagerVO findPersons(int parentId) {
		String hql = "select p.id,p.name,p.sex,p.phoneNumber from Person p where p.parent.id = ?";
		//当没有额外查询条件时，只需传递parentId（）
		return searchPaginated(hql, parentId);
	}
	@Override
	public PagerVO findPersons(int parentId, String sSearch) {
		String hql = "select p.id,p.name,p.sex,p.phoneNumber from Person p where p.parent.id = ? and p.name like ? ";
		Object[] params = {parentId,sSearch};//当有多个查询条件时，以数组形式传递
		
		return searchPaginated(hql, params);
	}
	@Override
	public PagerVO getAllPersons(String sSearch) {
		String hql = "select p.id,p.name,p.sex,p.phoneNumber from Person p where p.name like ? ";
		//当查询公司所有的员工时
		return searchPaginated(hql, sSearch);
	}

}
