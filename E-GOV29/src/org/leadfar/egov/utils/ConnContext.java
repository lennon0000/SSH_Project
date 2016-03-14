package org.leadfar.egov.utils;

import java.sql.Connection;

import org.leadfar.egov.model.User;

public class ConnContext {
	private static ThreadLocal connThread = new ThreadLocal();
	
	public static void setConnContext (Connection conn) {//注：在这里本来是通过调用setPageModel()方法时，将传入的值传给实例出来的静态pm，
		connThread.set(conn);
	}
	public static Connection getConnContext () {
		return (Connection)connThread.get();//这是获得方式
	}
	public static void removeConnection(){
		connThread.remove();
	}

	
	
}