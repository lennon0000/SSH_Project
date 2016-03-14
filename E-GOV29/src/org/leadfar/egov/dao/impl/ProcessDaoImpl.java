package org.leadfar.egov.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.leadfar.egov.dao.ProcessDao;
import org.leadfar.egov.model.BusinessProcess;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.User;
import org.leadfar.egov.utils.ConnContext;
import org.leadfar.egov.utils.DB;

public class ProcessDaoImpl implements ProcessDao {

	public void save(BusinessProcess bp) {
		String sql = "insert into t_process (id,step,user_id,start_time,accept_no) values(?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, bp.getId());
			pstmt.setString(2, bp.getStep());
			pstmt.setInt(3, bp.getUser().getId());
			//pstmt.setDate(4, (Date) bp.getStartTime());
			pstmt.setTimestamp(4, new java.sql.Timestamp(bp.getStartTime().getTime()));
			//pstmt.setString(5, bp.getSuggestion());
			pstmt.setString(5,bp.getId().substring(0,13));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			DB.close(pstmt);
		}
	}

	public BusinessProcess get(String id) {
		String sql = "select a.*,b.username,b.name,b.dept,b.admcode,b.id bid from t_process a join t_user b on a.user_id=b.id where a.id=?";


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BusinessProcess bp = null;
		try {
			
			pstmt = ConnContext.getConnContext().prepareStatement(sql);

			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				bp = new BusinessProcess();
				
				bp.setId(rs.getString("id"));
				bp.setStep(rs.getString("step"));
				
				bp.setStartTime(new java.sql.Timestamp(rs.getTimestamp("start_time").getTime()));
				bp.setEndTime(rs.getTimestamp("end_time"));
				bp.setSuggestion(rs.getString("suggestion"));
				
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setId(rs.getInt("bid"));
				bp.setUser(user);
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
		}
		return bp;
	}

	public void update(BusinessProcess bp) {
		String sql = "update t_process set step=?,user_id=?,start_time=?,end_time=?,accept_no=?,suggestion=? where id=? ";
		PreparedStatement pstmt = null;

		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			
			pstmt.setString(1, bp.getStep());
			pstmt.setInt(2, bp.getUser().getId());
			if(bp.getStartTime()!=null){
				pstmt.setTimestamp(3, new java.sql.Timestamp(bp.getStartTime().getTime()));
			}else{
				pstmt.setTimestamp(3, null);
			}
			if(bp.getEndTime()!=null){
				pstmt.setTimestamp(4, new java.sql.Timestamp(bp.getEndTime().getTime()));
			}else{
				pstmt.setTimestamp(4, null);
			}
			
			pstmt.setString(5,bp.getId().substring(0,13));
			pstmt.setString(6, bp.getSuggestion());
			pstmt.setString(7, bp.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			DB.close(pstmt);
		}
		
	}

	public void delete(String id) {
		String sql = "delete from t_process where id=? ";
		PreparedStatement pstmt = null;

		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			
			pstmt.setString(1,id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			DB.close(pstmt);
		}
	}

	public List<BusinessProcess> query(String acceptNo) {
		String sql = "select a.*,b.username,b.name,b.dept,b.admcode,b.id bid from t_process a join t_user b on a.user_id=b.id where a.accept_no=?";


		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BusinessProcess> bps = new ArrayList<BusinessProcess>();
		try {
			
			pstmt = ConnContext.getConnContext().prepareStatement(sql);

			pstmt.setString(1, acceptNo);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				BusinessProcess bp = new BusinessProcess();
				
				bp.setId(rs.getString("id"));
				bp.setStep(rs.getString("step"));
				
				bp.setStartTime(new java.sql.Timestamp(rs.getTimestamp("start_time").getTime()));
				bp.setEndTime(rs.getTimestamp("end_time"));
				bp.setSuggestion(rs.getString("suggestion"));
				
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setId(rs.getInt("bid"));
				bp.setUser(user);
				bps.add(bp);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
		}
		return bps;
	}

}
