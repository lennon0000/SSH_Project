package cn.com.leadfar.oa.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.leadfar.oa.dao.PartyDao;
import cn.com.leadfar.oa.dao.RoleDao;
import cn.com.leadfar.oa.dao.UserDao;
import cn.com.leadfar.oa.dao.UserRolesDao;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.model.UserRoles;
import cn.com.leadfar.oa.partyVO.PagerVO;
import cn.com.leadfar.oa.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Resource
	private RoleDao roleDao;
	@Resource
	private UserRolesDao userRolesDao;
	@Resource
	private PartyDao partyDao;


	@Override
	public void save(User user, List<Integer> roleIds) {

		Person person = partyDao
				.getById(Person.class, user.getPerson().getId());
		user.setPerson(person);
		userDao.save(user);// 先存user，在存，userRoles，

		Set<UserRoles> userRoles = new HashSet<UserRoles>();
		if (roleIds != null) {
			for (Integer roleId : roleIds) {
				UserRoles userRole = new UserRoles();
				userRole.setRole(roleDao.getById(Role.class, roleId));
				userRole.setUser(user);
				userRoles.add(userRole);
			}
		} 

		for (UserRoles userRole : userRoles) {
			userRolesDao.save(userRole);// TODO:一对多的关联中，是否需要两遍都进行保存，还是存到一遍，另一边自动建立链接
		}

	}

	@Override
	public void listUsers(PagerVO pagerVOs, String sSearch) {
		if (sSearch == null) {
			pagerVOs.addPagerVO(userDao.getAllUsers());
		} else {
			pagerVOs.addPagerVO(userDao.getAllUsers("%" + sSearch + "%"));
		}

	}

	@Override
	public User getUserById(int id) {
		return userDao.getById(User.class, id);
	}

	@Override
	public void update(User user, List<Integer> roleIds) {
		userDao.update(user);// 先存user，在存，userRoles，

		List<UserRoles> userRolesToDel = userRolesDao.getRoles(user);
		for (UserRoles userRole : userRolesToDel) {
			userRolesDao.del(userRole);
		}

		Set<UserRoles> userRoles = new HashSet<UserRoles>();
		for (Integer roleId : roleIds) {
			UserRoles userRole = new UserRoles();
			userRole.setRole(roleDao.getById(Role.class, roleId));
			userRole.setUser(user);
			userRoles.add(userRole);
		}
		for (UserRoles userRole : userRoles) {
			userRolesDao.save(userRole);// TODO:一对多的关联中，是否需要两遍都进行保存，还是存到一遍，另一边自动建立链接
		}

	}

	@Override
	public void del(User user) {

		User delUser = userDao.getUserById(user.getId());
		List<UserRoles> userRoles = userRolesDao.getRoles(user);
		for (UserRoles userRole : userRoles) {
			userRolesDao.del(userRole);
		}

		userDao.del(delUser);

	}

	@Override
	public List listUsersOnly(String sSearch) {
		if (sSearch == null) {
			return userDao.getAllUsersOnly();
		} else {
			return userDao.getAllUsersOnly("%" + sSearch + "%");
		}

	}

	@Override
	public User getUserByNamePassword(String userName, String password) {
		return userDao.getUserByNamePassword(userName,password);
	}

}
