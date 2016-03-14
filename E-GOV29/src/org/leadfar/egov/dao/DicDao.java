package org.leadfar.egov.dao;

import java.util.List;

import org.leadfar.egov.model.Dic;

public interface DicDao {

	//����dic����
	
	public void save(Dic dic);
	
	
	//�޸�dic����
	
	public void update(Dic dic);
	
	//����idɾ��һ��dic����
	
	public void delete(int id);
	
	//����id��ѯһ��dic����
	
	public Dic get(int id);
	
	//��ѯһ��dic����ļ���
	
	public List<Dic> query(String type);
}
