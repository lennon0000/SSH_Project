package org.leadfar.egov.service;

import java.util.List;

import org.leadfar.egov.model.Business;
import org.leadfar.egov.model.User;

public interface BusinessService {
	
	public void add(Business b,User user);
	public List<Business>  search(String acceptNo,User user);
	public void finish(String acceptNo);
	public void release(String acceptNo);
	public void lock(String acceptNo, User user);
	//
	public void lockRecord(String acceptNo, User user);
	public void releaseRecord(String acceptNo);
	public void finishRecord(String acceptNo);
	public List<Business> searchRecord(String acceptNo, User user);
	//
	public void lockCheck(String acceptNo, User user);
	public void releaseCheck(String acceptNo);
	public List<Business> searchCheck(String acceptNo, User user);
	
	public void finishCheck(String acceptNo, String suggestion);
	public void backCheck(String acceptNo, String suggestion);
	//
	public List<Business> searchApprove(String acceptNo, User currentUser);
	public void finishApprove(String acceptNo, String suggestion);
	public void backApprove(String acceptNo, String suggestion);
	public void releaseApprove(String acceptNo);
	public void lockApprove(String acceptNo, User currentUser);
	
	public Business getById(String acceptNo);
	
}
