package org.leadfar.egov.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {

	public static Connection getConn() {
		// 1.注册驱动

		// new oracle.jdbc.driver.OracleDriver();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// 2.创建Connection(数据库连接对象)
		String url = "jdbc:oracle:thin:@localhost:1521:leadfar";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, "egov", "egov");
			conn.setAutoCommit(false);
			return conn;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;

	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void close(Statement  stmt) {
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet  rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if (conn != null) {
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if (conn != null) {
				conn.rollback();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	public static void main(String[] args) {
		System.out.println(getConn());
	}
}
