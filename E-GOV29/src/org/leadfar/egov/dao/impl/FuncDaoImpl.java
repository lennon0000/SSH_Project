package org.leadfar.egov.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.leadfar.egov.dao.FuncDao;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.Func;
import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.User;
import org.leadfar.egov.utils.ConnContext;
import org.leadfar.egov.utils.DB;
import org.leadfar.egov.utils.PageModelContext;
import org.leadfar.egov.utils.RequestUtils;

public class FuncDaoImpl implements FuncDao {

	public List<Func> query() {
		String sql = "select * from t_func where pid is null";
		
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<Func> funcs = new ArrayList<Func>();
	
		try {
			
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				Func func = new Func();

				func.setId(rs.getString("id"));
				func.setName(rs.getString("name"));
				func.setUrl(rs.getString("url"));
				func.setParent(null);
				func.setChildren(query(func));
				
				funcs.add(func);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
//			DB.close(conn);
		}
		return funcs;

	}
	public List<Func> query(Func parent) {
		String sql = "select * from t_func where pid=?";
		
//		Connection conn = DB.getConn();
		
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		List<Func> funcs = new ArrayList<Func>();
	
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1,parent.getId());
			
			rs=pstmt.executeQuery();
			while(rs.next()){
				Func func = new Func();

				func.setId(rs.getString("id"));
				func.setName(rs.getString("name"));
				func.setUrl(rs.getString("url"));
				func.setParent(parent);
				func.setChildren(null);
				
				funcs.add(func);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
//			DB.close(conn);
		}
		return funcs;

	}

}
