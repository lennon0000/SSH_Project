package org.leadfar.egov.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.leadfar.egov.utils.ConnContext;
import org.leadfar.egov.utils.DB;

public class BaseDao {
	protected int getRowCount(String sql,Object[] params){
		sql="select count(*) "+sql.substring(sql.indexOf("from"));//这里是为了简化查询语句总sql的定义，所以直接把query中的sql传过来，然后针对性的选择
//		Connection conn = DB.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rowCount = 0;
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			for(int i=0 ;i<params.length;i++){
				if(params[i].getClass()== String.class){
					pstmt.setString(i+1, (String)params[i]);
				}
				if(params[i].getClass()== Integer.class){
					pstmt.setInt(i+1, (Integer)params[i]);
				}
				if(params[i].getClass()== Double.class){
					pstmt.setDouble(i+1, (Double)params[i]);
				}
				if(params[i].getClass()== Date.class){
					pstmt.setDate(i+1, new java.sql.Date(((Date)params[i]).getTime()));
				}
			}
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				rowCount = rs.getInt(1);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			DB.close(rs);
			DB.close(pstmt);
//			DB.close(conn);
		}
		return rowCount;//注意，因为目的是为了获得总条数，即count（*），上边根据条件在数据库中操作结束后获得的是一个表，表中只有一个数据，即count的总数
	}
}
