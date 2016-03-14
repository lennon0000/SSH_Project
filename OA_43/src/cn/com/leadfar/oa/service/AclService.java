package cn.com.leadfar.oa.service;

import java.util.List;
import java.util.Set;

import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.partyVO.AuthVO;


public interface AclService {

	void addOrUpdatePermission(String principalType, int principalId,
			String string, List<AuthVO> authvos);

	List<AuthVO> getAcls(String principalType, int principalId, String type);

	List<Menu> getAllPermitMenu(int id);

	boolean operHasPermit(int userId,String resourceSn, String oper);
	
}
