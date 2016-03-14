package cn.com.leadfar.oa.dao;

import java.util.List;

import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.partyVO.PagerVO;


public interface UserDao extends BaseDao {

	PagerVO getAllUsers(String sSearch);

	PagerVO getAllUsers();

	User getUserById(int id);

	void evict(Person person);

	List getAllUsersOnly();

	List getAllUsersOnly(String string);

	User getUserByNamePassword(String userName, String password);


}
