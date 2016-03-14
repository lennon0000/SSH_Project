package cn.com.leadfar.oa.model;

import java.util.List;

public interface SysResource {

	public int getResourceId();
	public String getResourceType();
	public List<SysResource> getChildrenResource();

}
