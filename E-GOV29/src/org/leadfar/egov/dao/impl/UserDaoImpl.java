package org.leadfar.egov.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.leadfar.egov.dao.DicDao;
import org.leadfar.egov.dao.UserDao;
import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.Func;
import org.leadfar.egov.model.PageModel;
import org.leadfar.egov.model.User;
import org.leadfar.egov.utils.ConnContext;
import org.leadfar.egov.utils.DB;
import org.leadfar.egov.utils.PageModelContext;
import org.omg.CORBA.Request;

public class UserDaoImpl extends BaseDao implements UserDao {

	public void save(User user) {
		String sql = "insert into t_user(username,admcode,password,status,name,dept,ip) values(?,?,?,?,?,?,?)";
	//	Connection conn = DB.getConn();

		PreparedStatement pstmt = null;

		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getAdm().getCode());// 注意根据需要传入数据中的数据，来设定选择值，
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getStatus());
			pstmt.setString(5, user.getName());
			pstmt.setString(6, user.getDept());
			pstmt.setString(7, user.getIp());

			pstmt.executeUpdate();
		//	DB.commit(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
			
		//	DB.rollback(conn);
		} finally {
			DB.close(pstmt);
	//		DB.close(conn);
		}

	}

	public void delete(int id) {
		String sql = "delete from t_user where id=?";
	//	Connection conn = DB.getConn();

		PreparedStatement pstmt = null;

		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setInt(1, id);
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

	public void update(User user) {
		String sql = "update t_user set username=?,admcode=?,password=?,status=?,name=?,dept=?,ip=? where id=?";
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;

		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);

			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getAdm().getCode());// 注意根据需要传入数据中的数据，来设定选择值，
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getStatus());
			pstmt.setString(5, user.getName());
			pstmt.setString(6, user.getDept());
			pstmt.setString(7, user.getIp());

			pstmt.setInt(8, user.getId());
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

	public User get(int id) {// 注意，在调用这个方法时我们想要获取的是user的对象，其中除了一些存在于t_user表中的id，username，等等，还希望通过admcode从t_dic中获得code为admcode的dic对象，因为这个对象中有admcode具体代表的中文值即value，
		String sql = "select a.*,b.id bid ,b.code,b.value,b.type from t_user a join t_dic b on a.admcode = b.code where b.type='AdmName' and a.id=?";
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Dic dic = new Dic();

				user = new User();
				user.setId(id);
				user.setUsername(rs.getString("username"));
				dic.setCode(rs.getString("admcode"));// 通过从数据库取出的admcode，可以获得一个code为admcode的dic对象
				dic.setValue(rs.getString("value"));
				user.setAdm(dic);// 根据从数据库取出的对应的值，是admcode，但是user.setAdm传入值应为Dic类型
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getString("status"));
				user.setName(rs.getString("name"));
				user.setDept(rs.getString("dept"));
				user.setIp(rs.getString("ip"));

				user.setFuncs(this.queryPermission(id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
//			DB.close(conn);
		}
		return user;
	}

	public List<Func> queryPermission(int userId) {// 注意，在调用这个方法时我们想要获取的是user的对象，其中除了一些存在于t_user表中的id，username，等等，还希望通过admcode从t_dic中获得code为admcode的dic对象，因为这个对象中有admcode具体代表的中文值即value，
		String sql = "select b.* from t_permission a join t_func b on a.func_id=b.id where user_id=?";
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Func> funcs = new ArrayList<Func>();
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Func func = new Func();

				func.setId(rs.getString("id"));// 注意，这里从数据库取值赋给func时不用获得parent和children的值，因为这样比较简单，在客户那边不在乎结构模型，只想表现出哪个选择了，所以只传这些就行
				func.setName(rs.getString("name"));
				func.setUrl(rs.getString("url"));

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

	public User getUserByAdmcodeAndUsername(String admcode, String username) {
		String sql = "select a.*,b.id bid ,b.code,b.value,b.type from t_user a join t_dic b on a.admcode = b.code and b.type='AdmName' where a.admcode=? and a.username=?";
//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			pstmt.setString(1, admcode);
			pstmt.setString(2, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Dic dic = new Dic();

				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(username);
				dic.setCode(admcode);// 通过从数据库取出的admcode，可以获得一个code为admcode的dic对象
				dic.setValue(rs.getString("value"));
				user.setAdm(dic);// 根据从数据库取出的对应的值，是admcode，但是user.setAdm传入值应为Dic类型
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getString("status"));
				user.setName(rs.getString("name"));
				user.setDept(rs.getString("dept"));
				user.setIp(rs.getString("ip"));
				// List<Func> funcs = new ArrayList<Func>();

				user.setFuncs(this.queryPermission(user.getId()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
	//		DB.close(conn);
		}
		return user;
	}

	public List<User> query(String admcode, String username) {

		String sql = "select a.*,b.id dic_id,b.code,b.value,b.type,rownum r from t_user a join t_dic b on a.admcode=b.code and b.type='AdmName'";
		PageModel pm = PageModelContext.getPageModel();

		if (admcode.equals("") || admcode == null) {// 注意这里是equals“”
			sql += " where (username like ? or name like ?)";
			pm.setRowCount(getRowCount(sql, new Object[] {
					"%" + username + "%", "%" + username + "%" }));// 注意，因为这里是要获得记录的总数，所以得放到中间，如果放到下边的下边的话，搜索条件中就加上了页面设置，就不是总数了，所以放中间
		} else {
			sql += " where admcode=? and (username like ? or name like ?)";
			pm.setRowCount(getRowCount(sql, new Object[] { admcode,
					"%" + username + "%", "%" + username + "%" }));// 注意，因为这里是要获得记录的总数，所以得放到中间，如果放到下边的下边的话，搜索条件中就加上了页面设置，就不是总数了，所以放中间
		}

		sql += " and rownum <= ?";
		sql = "select * from  (" + sql + ") where r>?";

//		Connection conn = DB.getConn();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		// int pageNo=1;
		try {

			pstmt = ConnContext.getConnContext().prepareStatement(sql);
			if (admcode.equals("")) {// 注意这里是equals“”
				pstmt.setString(1, "%" + username + "%");
				pstmt.setString(2, "%" + username + "%");
				pstmt.setInt(3, pm.getEndIndex());
				pstmt.setInt(4, pm.getStartIndex());
			} else {
				pstmt.setString(1, admcode);
				pstmt.setString(2, "%" + username + "%");
				pstmt.setString(3, "%" + username + "%");
				pstmt.setInt(4, pm.getEndIndex());
				pstmt.setInt(5, pm.getStartIndex());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Dic dic = new Dic();

				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				dic.setId(rs.getInt("dic_id"));
				dic.setCode(rs.getString("admcode"));
				dic.setValue(rs.getString("value"));
				dic.setType(rs.getString("type"));
				user.setAdm(dic);// 根据从数据库取出的对应的值，是admcode，但是user.setAdm传入值应为Dic类型
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getString("status"));
				user.setName(rs.getString("name"));
				user.setDept(rs.getString("dept"));
				user.setIp(rs.getString("ip"));
				user.setFuncs(this.queryPermission(user.getId()));
				users.add(user);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.close(rs);
			DB.close(pstmt);
//			DB.close(conn);
		}
		return users;

	}

	public void savePermissionByUser(int userId, String[] funcIds) {
		String sql = "insert into t_permission (user_id,func_id) values(?,?)";
	//	Connection conn = DB.getConn();
//		Connection conn = ConnContext.getConnContext();
		
		PreparedStatement pstmt = null;

		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);// 这边需要加强~！！！
			for (String funcId : funcIds) {
				pstmt.setInt(1, userId);
				pstmt.setString(2, funcId);
				pstmt.addBatch();
			}

			pstmt.executeBatch();
		//	DB.commit(conn);
		} catch (SQLException e) {
		//	e.printStackTrace();
			throw new RuntimeException(e);
		//	DB.rollback(conn);
		} finally {
			DB.close(pstmt);
	//		DB.close(conn);
		}

	}

	public void deletePermissionByUser(int userId) {
		String sql = "delete from t_permission where user_id=?";
	//	Connection conn = DB.getConn();
//		Connection conn = ConnContext.getConnContext();
		PreparedStatement pstmt = null;

		try {
			pstmt = ConnContext.getConnContext().prepareStatement(sql);// 这边需要加强~！！！
			pstmt.setInt(1, userId);

			pstmt.executeUpdate();
	//		DB.commit(conn);
		} catch (SQLException e) {
		//	e.printStackTrace();
			throw new RuntimeException(e);
	//		DB.rollback(conn);
		} finally {
			DB.close(pstmt);
	//		DB.close(conn);
		}

	}

}
