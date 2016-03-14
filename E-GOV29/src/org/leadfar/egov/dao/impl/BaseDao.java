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
		sql="select count(*) "+sql.substring(sql.indexOf("from"));//������Ϊ�˼򻯲�ѯ�����sql�Ķ��壬����ֱ�Ӱ�query�е�sql��������Ȼ������Ե�ѡ��
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
		return rowCount;//ע�⣬��ΪĿ����Ϊ�˻������������count��*�����ϱ߸������������ݿ��в����������õ���һ��������ֻ��һ�����ݣ���count������
	}
}
