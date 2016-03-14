package cn.com.leadfar.oa.dao;

import java.util.List;

import cn.com.leadfar.oa.model.ActionResource;

public interface ResourceDao extends BaseDao{
	public void delAllActionResources();
	public ActionResource findActionResourceBySn(String sn);
	
	/**
	 * 无条件查询所有的ActionResource对象
	 * @return
	 */
	public List<ActionResource> findAll();
	
	/**
	 * 查找所有顶级的ActionResource对象（即没有parent的ActionResource对象）
	 * @return
	 */
	public List<ActionResource> findAllTopActionResource();
	
	public void update(ActionResource ar);
	public ActionResource findActionResourceByName(String className);
}
