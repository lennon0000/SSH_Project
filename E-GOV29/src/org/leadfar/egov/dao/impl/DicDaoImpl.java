package org.leadfar.egov.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.leadfar.egov.dao.DicDao;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.utils.ConnContext;
import org.leadfar.egov.utils.DB;

public class DicDaoImpl implements DicDao {

	public void delete(int id) {
		String sql = "delete from t_dic where id=?";
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;

		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
	//		DB.commit(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
//			DB.rollback(conn);
		} finally {
			DB.close(pstmt);
//			DB.close(conn);
		}
	}

	public Dic get(int id) {
		String sql = "select * from t_dic where id=? ";
	//	Connection conn = DB.getConn();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		Dic dic=null;
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dic=new Dic();
				dic.setId(rs.getInt("id"));
				dic.setCode(rs.getString("code"));
				dic.setValue(rs.getString("value"));
				dic.setType(rs.getString("type"));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
//			DB.close(conn);
		}
		return dic;
	}

	public List<Dic> query(String type) {
		String sql = "select * from t_dic where type=? ";
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<Dic> dics=new ArrayList<Dic>();
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, type);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Dic	dic=new Dic();
				dic.setId(rs.getInt("id"));
				dic.setCode(rs.getString("code"));
				dic.setValue(rs.getString("value"));
				dic.setType(rs.getString("type"));
				dics.add(dic);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
//			DB.close(conn);
		}
		return dics;
	}

	public void save(Dic dic) {
		String sql = "insert into t_dic(code,value,type) values(?,?,?)";
	//	Connection conn = DB.getConn();

		PreparedStatement pstmt = null;

		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, dic.getCode());
			pstmt.setString(2, dic.getValue());
			pstmt.setString(3, dic.getType());
			pstmt.executeUpdate();
//			DB.commit(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
//			DB.rollback(conn);
		} finally {
			DB.close(pstmt);
//			DB.close(conn);
		}

	}

	public void update(Dic dic) {
		String sql = "update t_dic set code=?,value=?,type=? where id=?";
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;

		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, dic.getCode());
			pstmt.setString(2, dic.getValue());
			pstmt.setString(3, dic.getType());
			pstmt.setInt(4, dic.getId());
			pstmt.executeUpdate();
//			DB.commit(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
//			DB.rollback(conn);
		} finally {
			DB.close(pstmt);
//			DB.close(conn);
		}
	}

}
