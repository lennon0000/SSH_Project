package org.leadfar.egov.dao;

import java.util.List;

import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.User;

public interface UserDao {
	public void save(User user);
	public void delete(int id);
	public void update(User user);
	public User get(int id);
	public User getUserByAdmcodeAndUsername(String admcode,String username);
	public List<User> query(String admcode,String username);//注意这里查的时候不是列出全部的list，而是根据实际情况有条件的查询
	public void savePermissionByUser(int userId, String[] funcIds);
	public void deletePermissionByUser(int userId);
}
