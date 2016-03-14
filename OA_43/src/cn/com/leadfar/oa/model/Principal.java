package cn.com.leadfar.oa.model;

import java.util.List;

public interface Principal {

	String getPrincipalType();

	int getPrincipalId();

	List<Principal> getParentPrincipal();

}
