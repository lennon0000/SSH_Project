package org.leadfar.egov.service;

import java.util.List;

import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.User;

public interface UserService {
public void add(User user);
public void modify(User user);
public User getById(int id);
public List<User> search(String admcode,String username);
public void enable(int id);
public void disable(int id);
public void addPermissionByUser(int userId, String[] funcIds);
//public int login(User user);
public void userActive(int id);
public User login(String admcode, String username, String password, String ip);
public void modifyPassword(User loginUser, String password1, String password2);
public void binding(String ip, User user);

}
