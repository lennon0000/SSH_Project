package org.leadfar.egov.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.leadfar.egov.dao.EnterpriseDao;
import org.leadfar.egov.model.ep.Enterprise;
import org.leadfar.egov.model.ep.EnterpriseFile;
import org.leadfar.egov.model.ep.StockHolder;
import org.leadfar.egov.utils.MyBatisUtils;
import org.leadfar.egov.utils.SqlSessionContext;
import org.leadfar.egov.utils.SqlSessionHelper;

public class EnterpriseDaoImpl implements EnterpriseDao {

	public void save(Enterprise e) {
		// MyBatisUtils.getSession().insert(Enterprise.class.getName(), e);
		// 注意，这里是打开myBatis连接，然后调用操作数据库的方法，由于考虑事物，所以，跟JDBC中一样
		// ，需利用SqlSessionContext和SqlSessionHelper代理来进行打开连接和进行提交等操作
		// 所以建立SqlSessionContext和SqlSessionHelper
		
		SqlSessionContext.getSessionContext().insert(
				Enterprise.class.getName() + ".insert", e);
		// 利用SqlSessionContext和SqlSessionHelper代理之后的形式
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String createRegisterNo(Enterprise e) {
		String strNo = e.getAdm().getCode() + e.getEnterpriseType().getCode();
		System.out.println(strNo);
		Map param = new HashMap();
		param.put("min", strNo + "00001");// TODO:应该是否带单引号
		param.put("max", strNo + "99999");
		
		if ((String) SqlSessionContext.getSessionContext().selectOne(
				Enterprise.class.getName() + ".createRegisterNo", param) == null) {
			return strNo + "00001";
		} else {
			return String.valueOf((Long.parseLong((String) SqlSessionContext.getSessionContext().selectOne(
					Enterprise.class.getName() + ".createRegisterNo", param))+1));
		}//注，实现字符串+1，是先转换成long类型，然后+1，再转换成String类型
	}

	public Enterprise get(int id) {
	//	Enterprise e = (Enterprise)SqlSessionContext.getSessionContext().selectOne(Enterprise.class.getName() + ".get", id);
		return (Enterprise)SqlSessionContext.getSessionContext().selectOne(Enterprise.class.getName() + ".get", id);
	}

	public void update(Enterprise e) {
		
		SqlSessionContext.getSessionContext().update(Enterprise.class.getName() + ".update", e);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateBusiness(String acceptNo, int enterpriseId) {
		Map params = new HashMap();
		params.put("acceptNo", acceptNo);//TODO:没有获得acceptNo
		params.put("enterpriseId", enterpriseId);
		SqlSessionContext.getSessionContext().update(Enterprise.class.getName() + ".updateBusiness", params);
		
	}

	public int getEnterpriseId(String acceptNo) {
		return (Integer) SqlSessionContext.getSessionContext().selectOne(Enterprise.class.getName()+".getEnterpriseId",acceptNo);
	}

	public void saveStockHolders(StockHolder stockHolder) {
		
		SqlSessionContext.getSessionContext().insert(Enterprise.class.getName()+".saveStockHolders",stockHolder);
		
	}

	public void deleteStockHolders(int enterpriseId) {
		SqlSessionContext.getSessionContext().delete(Enterprise.class.getName()+".deleteStockHolders",enterpriseId);
		
	}

	public List<StockHolder> getStockHolderList(int enterpriseId) {
		
		return SqlSessionContext.getSessionContext().selectList(Enterprise.class.getName()+".getStockHolderList",enterpriseId);
	}

	public void save(EnterpriseFile ef) {
		SqlSessionContext.getSessionContext().insert(Enterprise.class.getName()+".saveEF",ef);
		
	}

	public void delEnterpriseFile(int id) {
		SqlSessionContext.getSessionContext().delete(Enterprise.class.getName()+".delEnterpriseFile",id);
	}

	public EnterpriseFile getEnterpriseFileById(int id) {
		return	(EnterpriseFile) SqlSessionContext.getSessionContext().selectOne(Enterprise.class.getName()+".getEnterpriseFileById",id);
	}

}
