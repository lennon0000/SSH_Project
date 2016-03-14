package org.leadfar.egov.utils;

import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.User;

public class UserContext {
	//public static User user;
	private static ThreadLocal userThread = new ThreadLocal();
	
	public static void setUserContext (User user) {//ע�������ﱾ����ͨ������setPageModel()����ʱ���������ֵ����ʵ�������ľ�̬pm��
		//PageModelContext.pm=pm;//���������ַ���ʱ����̬����������ϵͳӦ�����ǹ��õģ����Ե�����ʹ��ʱ��������ݱ���ģ����Ծ���Ҫ����java�Դ���һ��
		//threadlocal�������ʱ�������pm��ֵ���������new�����Ķ�������������˵���ʱ���������ÿ������pm���˲���һ�������ĸ����������໥Ӱ�죻
	userThread.set(user);
	}
	public static User getUserContext () {
		//return PageModelContext.pm;
		return (User)userThread.get();//���ǻ�÷�ʽ
	}
	public static void removeUser(){
		userThread.remove();
	}

	
	
}
