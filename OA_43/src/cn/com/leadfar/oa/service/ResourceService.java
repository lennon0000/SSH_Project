package cn.com.leadfar.oa.service;

import java.util.List;

import cn.com.leadfar.oa.model.ActionMethodOper;
import cn.com.leadfar.oa.model.ActionResource;

public interface ResourceService {
	
	/**
	 * 重新搜索以及重建所有的ActionResource对象
	 */
	public void rebuildActionResources();
	
	/**
	 * 查找所有的ActionResources对象
	 * @return
	 */
	public List<ActionResource> findAllActionResources();
	
	/**
	 * 查找所有顶级的ActionResources对象
	 * @return
	 */
	public List<ActionResource> findAllTopActionResources();
	
	/**
	 * 添加ActionResource
	 * @param ar
	 */
	public void addActionResource(ActionResource ar);
	
	/**
	 * 更新ActionResource
	 * @param ar
	 */
	public void updateActionResource(ActionResource ar);
	
	/**
	 * 删除ActionResource
	 * @param actionResourceId
	 */
	public void delActionResource(int actionResourceId);
	
	/**
	 * 根据ID查询ActionResource对象
	 * @param actionResourceId
	 * @return
	 */
	public ActionResource findActionResourceById(int actionResourceId);
	
	/**
	 * 删除某个资源对应的某个操作
	 * @param actionResourceId
	 * @param operSn
	 */
	public void delActionResourceOper(int actionResourceId,String operSn);
	
	/**
	 * 添加资源的某个操作
	 * @param actionResourceId
	 * @param oper
	 */
	public void addActionResourceOper(int actionResourceId,ActionMethodOper oper);

	public ActionResource findActionResourceByClassName(String className);
	
}
