package org.leadfar.egov.dao;

import java.util.List;

import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.Relation;
import org.leadfar.egov.model.User;

public interface BusinessDao {
	public void save(Business b);
	//public void saveRelation(Business b);
	public void saveRelation(Relation r,String acceptno);
	public String createAcceptNo();
	public List<Business> query(String accetpNo, User user);
	public Business get(String acceptNo);
	public void update(Business b);
	public List<Business> query(String accetpNo, User user, String step,String ingStatus,String status);
}
