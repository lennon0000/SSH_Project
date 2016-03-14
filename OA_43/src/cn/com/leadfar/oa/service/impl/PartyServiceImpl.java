package cn.com.leadfar.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.leadfar.oa.dao.PartyDao;
import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.partyVO.PagerVO;
import cn.com.leadfar.oa.service.PartyService;
import cn.com.leadfar.oa.utils.SystemContext;

@Service("partyService")
public class PartyServiceImpl implements PartyService {
	@Resource
	private PartyDao partyDao;

	@Override
	public Party getParty(int partyId) {

		return partyDao.getById(Party.class, partyId);
	}

	@Override
	public void save(Party party) {
		partyDao.save(party);

	}

	@Override
	public void update(Party party) {
		partyDao.update(party);

	}

	@Override
	public void del(Party party) {
		List<Party> children = partyDao.delCheck(party);
		if (children.size() == 0) {
			partyDao.del(party);
		} else {
			throw new RuntimeException("有子节点，不能进行删除操作，请先删除子节点");
		}

	}

	@Override
	public List<Party> getParties() {

		return partyDao.getAll(Party.class);
	}

	@Override
	public List<Person> getPersons(Integer id) {// TODO:利用递归算法，找出所有的孩子person
		List<Person> persons = new ArrayList<Person>();
		persons = partyDao.getPersons(id);
		List<Party> children = partyDao.getChildren(id);
		if (children.size() != 0) {
			for (Party child : children) {
				List<Person> childsPerson = getPersons(child.getId());
				persons.addAll(childsPerson);
			}
		}
		return persons;
	}

	@Override
	public List<Person> listPersons(Integer id) {

		return partyDao.getPersons(id);
	}
	
	@Override
	public void findPersons(PagerVO pagerVOs,int parentId, String sSearch) {
		if (parentId != 0) {
			
			if (sSearch.equals("")) {
				PagerVO pagerVOs2 = partyDao.findPersons(parentId);//如果没有sSearch条件，则只通过parentId条件
				pagerVOs.addPagerVO(pagerVOs2);
			} else {
				PagerVO pagerVOs2 = partyDao.findPersons(parentId, "%"+sSearch+"%");
			}
			
			List<Party> children = partyDao.getChildren(parentId);
			if (children.size() != 0) {//利用递归，判断，如果该对象有孩子时，则遍历其孩子，通过孩子的id，来同样调用findPersons方法，同样需要查询条件
				for (Party child : children) {
					findPersons(pagerVOs,child.getId(), sSearch);
				}
			}
			
		} else {
			pagerVOs.addPagerVO(partyDao.getAllPersons("%"+sSearch+"%"));
		// 如果parentId为0，则为查公司全部的工作人员,针对“人员管理”的功能，
		//注意，需添加sSearch条件，实现搜索功能
		}

	}

}
