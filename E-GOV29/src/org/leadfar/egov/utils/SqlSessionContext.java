package org.leadfar.egov.utils;

import org.apache.ibatis.session.SqlSession;

public class SqlSessionContext {
	private static ThreadLocal sessionThread = new ThreadLocal();
	
	public static void setSessionContext (SqlSession session) {//ע�������ﱾ����ͨ������setPageModel()����ʱ���������ֵ����ʵ�������ľ�̬pm��
		sessionThread.set(session);
	}
	public static SqlSession getSessionContext () {
		return (SqlSession)sessionThread.get();//���ǻ�÷�ʽ
	}
	public static void removeConnection(){
		sessionThread.remove();
	}

	
	
}