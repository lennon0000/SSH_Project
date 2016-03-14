package cn.com.leadfar.oa.dao;

import java.util.List;

import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.partyVO.PagerVO;

public interface PartyDao extends BaseDao {

	List<Party> delCheck(Party party);

	List<Person> getPersons(Integer id);

	List<Party> getChildren(Integer id);

	PagerVO findPersons(int parentId, String sSearch);

	PagerVO getAllPersons(String sSearch);

	PagerVO findPersons(int parentId);

}
