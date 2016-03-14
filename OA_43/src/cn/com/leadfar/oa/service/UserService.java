package cn.com.leadfar.oa.service;

import java.util.List;

import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.partyVO.PagerVO;

public interface UserService {

	void listUsers(PagerVO pagerVOs,String sSearch);

	void save(User user, List<Integer> roleIds);

	User getUserById(int id);

	void update(User user, List<Integer> roleIds);

	void del(User user);

	List listUsersOnly(String sSearch);

	User getUserByNamePassword(String userName, String password);
	
}
