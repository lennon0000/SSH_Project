package cn.com.leadfar.oa.dao;

import java.util.List;

public interface BaseDao {
	public void save(Object object);
	public void update(Object object);
	public void del(Object object);
	public <T> T getById(Class<T> objectType,int id);
	public <T> List<T> getAll(Class<T> objectType);
}
