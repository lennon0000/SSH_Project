package org.leadfar.egov.utils;

import java.sql.Connection;

import org.leadfar.egov.model.User;

public class ConnContext {
	private static ThreadLocal connThread = new ThreadLocal();
	
	public static void setConnContext (Connection conn) {//ע�������ﱾ����ͨ������setPageModel()����ʱ���������ֵ����ʵ�������ľ�̬pm��
		connThread.set(conn);
	}
	public static Connection getConnContext () {
		return (Connection)connThread.get();//���ǻ�÷�ʽ
	}
	public static void removeConnection(){
		connThread.remove();
	}

	
	
}