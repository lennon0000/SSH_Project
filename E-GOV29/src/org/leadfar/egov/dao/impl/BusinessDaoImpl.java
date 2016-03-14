package org.leadfar.egov.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.leadfar.egov.dao.BusinessDao;
import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.Relation;
import org.leadfar.egov.model.User;
import org.leadfar.egov.utils.ConnContext;
import org.leadfar.egov.utils.DB;
import org.leadfar.egov.utils.DicConverter;
import org.leadfar.egov.utils.PageModelContext;

public class BusinessDaoImpl extends BaseDao implements BusinessDao {

	public void save(Business b) {
		String sql = "insert into t_business (accept_no,enterprise_name,create_time,admcode,business_type,process_status) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, b.getAcceptNo());
			pstmt.setString(2, b.getEnterpriseName());
			pstmt.setTimestamp(3, new Timestamp(b.getCreateTime().getTime()));
			// pstmt.setDate(3, new java.sql.Date(b.getCreateTime()));
			pstmt.setString(4, b.getAdm().getCode());
			pstmt.setString(5, b.getBusinessType().getCode());
			pstmt.setString(6, b.getProcessStatus().getCode());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			DB.close(pstmt);
		}

	}

	public void saveRelation(Relation r, String acceptno) {
		String sql = "insert into t_relation (accept_no,idnumber,name,phone,email) values(?,?,?,?,?)";
		PreparedStatement pstmt = null;

		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, acceptno);
			pstmt.setString(2, r.getIdNumber());
			pstmt.setString(3, r.getName());
			pstmt.setString(4, r.getPhone());
			pstmt.setString(5, r.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			DB.close(pstmt);
		}

	}

	public String createAcceptNo() {

		String sql = "select max(accept_no) from t_business where accept_no between ? and ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			String nowStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
			pstmt.setString(1, nowStr + "00001");
			pstmt.setString(2, nowStr + "99999");

			rs = pstmt.executeQuery();
			synchronized (this) {
				if (rs.next()) {
					if (rs.getString(1) != null) {
						return String.valueOf(Long.parseLong(rs.getString(1)) + 1);

					}
				}
			}
			
			return nowStr + "00001";

		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			DB.close(pstmt);
		}
	}

	public List<Business> query(String acceptNo, User user) {

		StringBuffer buffer = new StringBuffer();

		buffer.append(" select t.*,rownum r from ( ");

		buffer.append(" select a.*,b.idnumber,b.name,b.phone,b.email ");
		buffer.append(" from t_business a ");
		buffer.append(" join t_relation b on a.accept_no=b.accept_no ");
		buffer.append(" where admcode=? and process_status between '01' and '10' and a.accept_no like ? ");
		buffer.append(" and a.accept_no in (select accept_no from t_process where user_id=? and step=? and accept_no like ?) ");
		buffer.append(" union ");

		buffer.append(" select a.*,b.idnumber,b.name,b.phone,b.email  ");
		buffer.append(" from t_business a  ");
		buffer.append(" join t_relation b on a.accept_no=b.accept_no ");
		buffer.append(" where admcode=? and process_status='00' and a.accept_no like ? ");

		buffer.append(" ) t ");

		PageModelContext.getPageModel().setRowCount(
				this.getRowCount(buffer.toString(), new Object[] {
						user.getAdm().getCode(), acceptNo + "%", user.getId(),
						"A", acceptNo + "%", user.getAdm().getCode(),
						acceptNo + "%" }));

		String sql = "select * from (" + buffer.toString()
				+ ") where r<=? and r>?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Business> bs = new ArrayList<Business>();
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);

			pstmt.setString(1, user.getAdm().getCode());
			pstmt.setString(2, acceptNo + "%");
			pstmt.setInt(3, user.getId());
			pstmt.setString(4, "A");
			pstmt.setString(5, acceptNo + "%");
			pstmt.setString(6, user.getAdm().getCode());
			pstmt.setString(7, acceptNo + "%");

			pstmt.setInt(8, PageModelContext.getPageModel().getEndIndex());
			pstmt.setInt(9, PageModelContext.getPageModel().getStartIndex());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Business b = new Business();
				b.setAcceptNo(rs.getString("accept_no"));
				b.setCreateTime(rs.getTimestamp("create_time"));
				b.setEnterpriseName(rs.getString("enterprise_name"));

				Relation r = new Relation();
				r.setIdNumber(rs.getString("idnumber"));
				r.setEmail(rs.getString("email"));
				r.setName(rs.getString("name"));
				r.setPhone(rs.getString("phone"));
				b.setRelation(r);
				
				b.setAdm(DicConverter.convert("AdmName",rs.getString("admcode")));
				b.setBusinessType(DicConverter.convert("BusinessType",rs.getString("business_type")));
				b.setProcessStatus(DicConverter.convert("ProcessStatus",rs.getString("process_status")));
				bs.add(b);
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
		}
		return bs;

	}

	public Business get(String acceptNo) {
		String sql = " select a.*,b.idnumber,b.name,b.phone,b.email from t_business a join t_relation b on a.accept_no=b.accept_no where a.accept_no=? ";



		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Business b = null;
		try {
			
			pstmt = ConnContext.getConnContext().prepareStatement(sql);

			pstmt.setString(1, acceptNo);

			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				b = new Business();
				b.setAcceptNo(rs.getString("accept_no"));
				b.setCreateTime(rs.getTimestamp("create_time"));
				b.setEnterpriseName(rs.getString("enterprise_name"));

				Relation r = new Relation();
				r.setIdNumber(rs.getString("idnumber"));
				r.setEmail(rs.getString("email"));
				r.setName(rs.getString("name"));
				r.setPhone(rs.getString("phone"));
				b.setRelation(r);
				
				b.setAdm(DicConverter.convert("AdmName",rs.getString("admcode")));
				b.setBusinessType(DicConverter.convert("BusinessType",rs.getString("business_type")));
				b.setProcessStatus(DicConverter.convert("ProcessStatus",rs.getString("process_status")));
				
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
		}
		return b;

	}

	public void update(Business b) {
		String sql = "update t_business set enterprise_name=?,create_time=?,admcode=?,business_type=?,process_status=? where accept_no=? ";
		PreparedStatement pstmt = null;

		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			
			pstmt.setString(1, b.getEnterpriseName());
			pstmt.setTimestamp(2, new Timestamp(b.getCreateTime().getTime()));
			pstmt.setString(3, b.getAdm().getCode());
			pstmt.setString(4, b.getBusinessType().getCode());
			pstmt.setString(5, b.getProcessStatus().getCode());//TODO:更新
			pstmt.setString(6, b.getAcceptNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);

		} finally {
			DB.close(pstmt);
		}

		
	}

	public List<Business> query(String accetpNo, User user, String step,String ingStatus,String status) {
		StringBuffer buffer = new StringBuffer();

		buffer.append(" select t.*,rownum r from ( ");

		buffer.append(" select a.*,b.idnumber,b.name,b.phone,b.email ");
		buffer.append(" from t_business a ");
		buffer.append(" join t_relation b on a.accept_no=b.accept_no ");
		buffer.append(" where admcode=? and process_status in ("+ingStatus+") and a.accept_no like ? ");
		buffer.append(" and a.accept_no in (select accept_no from t_process where user_id=? and step=? and accept_no like ?) ");
		buffer.append(" union ");

		buffer.append(" select a.*,b.idnumber,b.name,b.phone,b.email  ");
		buffer.append(" from t_business a  ");
		buffer.append(" join t_relation b on a.accept_no=b.accept_no ");
		buffer.append(" where admcode=? and process_status='"+status+"' and a.accept_no like ? ");

		buffer.append(" ) t ");

		PageModelContext.getPageModel().setRowCount(
				this.getRowCount(buffer.toString(), new Object[] {
						user.getAdm().getCode(), accetpNo + "%", user.getId(),
						step, accetpNo + "%", user.getAdm().getCode(),
						accetpNo + "%" }));

		String sql = "select * from (" + buffer.toString()
				+ ") where r<=? and r>?";

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Business> bs = new ArrayList<Business>();
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);

			pstmt.setString(1, user.getAdm().getCode());
			pstmt.setString(2, accetpNo + "%");
			pstmt.setInt(3, user.getId());
			pstmt.setString(4, step);
			pstmt.setString(5, accetpNo + "%");
			pstmt.setString(6, user.getAdm().getCode());
			pstmt.setString(7, accetpNo + "%");

			pstmt.setInt(8, PageModelContext.getPageModel().getEndIndex());
			pstmt.setInt(9, PageModelContext.getPageModel().getStartIndex());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Business b = new Business();
				b.setAcceptNo(rs.getString("accept_no"));
				b.setCreateTime(rs.getTimestamp("create_time"));
				b.setEnterpriseName(rs.getString("enterprise_name"));

				Relation r = new Relation();
				r.setIdNumber(rs.getString("idnumber"));
				r.setEmail(rs.getString("email"));
				r.setName(rs.getString("name"));
				r.setPhone(rs.getString("phone"));
				b.setRelation(r);
				
				b.setAdm(DicConverter.convert("AdmName",rs.getString("admcode")));
				b.setBusinessType(DicConverter.convert("BusinessType",rs.getString("business_type")));
				b.setProcessStatus(DicConverter.convert("ProcessStatus",rs.getString("process_status")));
				bs.add(b);
				
				

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(pstmt);
		}
		return bs;
	}


}
