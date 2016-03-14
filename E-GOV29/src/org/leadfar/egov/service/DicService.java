package org.leadfar.egov.service;

import java.util.List;

import org.leadfar.egov.model.Dic;
import org.leadfar.egov.model.User;

public interface DicService {
	

	public void add(Dic dic);
	
	public void modify(Dic dic);
	
	public void delete(int id);
	
	public Dic getById(int id);
	
	public List<Dic> search(String type);


}
