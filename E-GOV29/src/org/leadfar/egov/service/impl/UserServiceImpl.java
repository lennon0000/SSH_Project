package org.leadfar.egov.service.impl;

import java.sql.Connection;
import java.util.List;

import org.leadfar.egov.dao.UserDao;
import org.leadfar.egov.model.User;
import org.leadfar.egov.service.UserService;
import org.leadfar.egov.utils.ConnContext;
import org.leadfar.egov.utils.DB;
import org.leadfar.egov.utils.MD5;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void add(User user) {
		user.setStatus("00");
		// TODO:������б���ʱ����ip
		user.setPassword(MD5.encode(user.getUsername()));// ע������~~������//TODO:������б���ʱ�������������
		this.userDao.save(user);
	}

	public void modify(User user) {
		this.userDao.update(user);

	}

	public User getById(int id) {
		return this.userDao.get(id);
	}

	public List<User> search(String admcode, String username) {
		return this.userDao.query(admcode, username);
	}

	public void enable(int id) {
		User user = this.userDao.get(id);
		user.setStatus("01");
		this.userDao.update(user);

	}

	public void disable(int id) {
		User user = this.userDao.get(id);
		user.setStatus("00");
		this.userDao.update(user);

	}

	public void addPermissionByUser(int userId, String[] funcIds) {
		Connection conn = DB.getConn();
		ConnContext.setConnContext(conn);
	//	try {
			this.userDao.deletePermissionByUser(userId);
			if (funcIds != null) {
				this.userDao.savePermissionByUser(userId, funcIds);
			}
	//		DB.commit(conn);
	//	} catch (Exception e) {
			
	//		DB.rollback(conn);
	//	}finally{
	//		DB.close(conn);
	//	}
		

	}

//	public int login(User user) {
//		Dic adm = user.getAdm();
//		String admcode = adm.getCode();
//		String username = user.getUsername();
//		String passwordGetFromJsp = MD5.encode(user.getPassword());// ע�⣬�����ƺ�Ҫ�����ͳһ�޸ģ������������������ܣ�
//		// TODO:�������������˼��ܣ���������ȶ� // user.setPassword(MD5.encode("123456"));//
//		// ע������~~������
//		User userChecked = userDao.getUserByAdmcodeAndUsername(admcode,
//				username);
//
//		UserContext.setUserContext(userChecked);
//
//		if (userChecked != null) {
//			if (userChecked.getPassword().equals(passwordGetFromJsp)) {
//				String status = userChecked.getStatus();
//				if (status.equals("00")) {
//
//					return 1;// �˻�δ����������
//				}
//				if (status.equals("02")) {
//					return 4;// �˻���ͣ��,�������ڹ���Ա�ڽ��в���ʱ��ֻ�ܶ�״̬����Ϊ�����δ��������������Ŀǰ���ᷢ��
//				} else {
//					return 2;// �ɹ���¼
//				}
//			} else {
//				return 3;// ���벻��ȷ��������ʾ���û�������벻��ȷ��
//			}
//
//		} else {
//			return 3;// �û�����ȷ��������ʾ���û�������벻��ȷ��
//		}
//	}

	public void userActive(int id) {//TODO:ע�⣬�û�������Էŵ����Ҳ���Էŵ�LoginService��

		User user = this.userDao.get(id);
		user.setStatus("01");
		this.userDao.update(user);

	}

	public User login(String admcode, String username, String password,
			String ip) {
		User user = this.userDao.getUserByAdmcodeAndUsername(admcode, username);
		
		if(user==null){
			throw new RuntimeException("�û������");
		}
		if(!MD5.encode(password).equals(user.getPassword())){
			throw new RuntimeException("�������");
		}
		if("02".equals(user.getStatus())){
			throw new RuntimeException("�û�����");
		}
		if(user.getIp()!=null){
			if(!ip.equals(user.getIp())){
				throw new RuntimeException("ip��ַ����");
			}
		}return user;
		
	}


	public void modifyPassword(User loginUser, String password1,
			String password2) {
		User user = this.userDao.get(loginUser.getId());
		String password = user.getPassword();
		
		if(!password.equals(MD5.encode(password1))){
			throw new RuntimeException("�����������������������");
		}else{
			user.setPassword(MD5.encode(password2));
			user.setStatus("01");
			userDao.update(user);
		}
		
	}


	public void binding(String ip, User user) {
		user.setIp(ip);
		this.userDao.update(user);
		
	}
}
