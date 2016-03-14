package org.leadfar.egov.utils;

import org.apache.ibatis.session.SqlSession;

public class SqlSessionContext {
	private static ThreadLocal sessionThread = new ThreadLocal();
	
	public static void setSessionContext (SqlSession session) {//注：在这里本来是通过调用setPageModel()方法时，将传入的值传给实例出来的静态pm，
		sessionThread.set(session);
	}
	public static SqlSession getSessionContext () {
		return (SqlSession)sessionThread.get();//这是获得方式
	}
	public static void removeConnection(){
		sessionThread.remove();
	}

	
	
}