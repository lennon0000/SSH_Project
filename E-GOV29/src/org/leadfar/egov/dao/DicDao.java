package org.leadfar.egov.dao;

import java.util.List;

import org.leadfar.egov.model.Dic;

public interface DicDao {

	//保存dic对象
	
	public void save(Dic dic);
	
	
	//修改dic对象
	
	public void update(Dic dic);
	
	//根据id删除一个dic对象
	
	public void delete(int id);
	
	//根据id查询一个dic对象
	
	public Dic get(int id);
	
	//查询一组dic对象的集合
	
	public List<Dic> query(String type);
}
