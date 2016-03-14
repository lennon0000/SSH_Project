package cn.com.leadfar.oa.service;

import java.util.List;

import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.partyVO.PagerVO;

public interface PartyService {
	public Party getParty(int partyId);

	public void save(Party party);

	public void update(Party party);
	
	public void del(Party party);
	
	public List<Party> getParties();//TODO：应该传入什么参数？

	public List<Person> getPersons(Integer id);

	public List<Person> listPersons(Integer id);

	public void findPersons(PagerVO pagerVOs, int parentId, String sSearch);

}
