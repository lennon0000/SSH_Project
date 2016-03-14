package cn.com.leadfar.oa.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.model.ACL;
import cn.com.leadfar.oa.model.ActionMethodOper;
import cn.com.leadfar.oa.model.ActionResource;
import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.model.Party;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.partyVO.AuthVO;
import cn.com.leadfar.oa.partyVO.MenuVO;
import cn.com.leadfar.oa.partyVO.PagerVO;
import cn.com.leadfar.oa.partyVO.PartyVO;
import cn.com.leadfar.oa.partyVO.PartyVOUpperCase;
import cn.com.leadfar.oa.partyVO.RoleVO;
import cn.com.leadfar.oa.service.AclService;
import cn.com.leadfar.oa.service.CompanyService;
import cn.com.leadfar.oa.service.MenuService;
import cn.com.leadfar.oa.service.ResourceService;
import cn.com.leadfar.oa.service.RoleService;
import cn.com.leadfar.oa.service.UserService;
import cn.com.leadfar.oa.utils.JSONUtils;
import cn.com.leadfar.oa.utils.SystemContext;

import com.opensymphony.xwork2.ActionContext;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

@Component
@Scope("prototype")
public class AclAction {
	private ACL acl;
	@Resource
	private RoleService roleService;
	@Resource
	private AclService aclService;
	@Resource
	private MenuService menuService;
	@Resource
	private ResourceService resourceService;
	@Resource
	private CompanyService companyService;
	@Resource
	private UserService userService;
	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	private String sSearch;
	
	private int topMenuId;
	private List param;
	private List<AuthVO> authvos;
	private String principalType;
	              
	private int principalId;
	
	public int getTopMenuId() {
		return topMenuId;
	}

	public void setTopMenuId(int topMenuId) {
		this.topMenuId = topMenuId;
	}

	public String getPrincipalType() {
		return principalType;
	}

	public void setPrincipalType(String principalType) {
		this.principalType = principalType;
	}

	public int getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(int principalId) {
		this.principalId = principalId;
	}

	
	

	public String allActionResource() {
		//为了生成action资源列表，所以进行查询，将集合输出至页面后，利用datatable tree进行格式控制
		List<ActionResource> ars = resourceService.findAllActionResources();
		
		ActionContext.getContext().put("ress", ars);
		
//		
		return "all_action_resource";
	}
	public String allMenuResource() {
		//获得所有一级Menu的id，传递到jsp，然后遍历，再利用ajax获得所有的树
		List<Integer> menuIds = menuService.getAllMenuIdS();
		
		ActionContext.getContext().put("menuIds", menuIds);
		
		return "menuResource_list";
	}
	public String roleAuthIndex() {

		return "role_acl_list";
	}
	public String userAuthIndex() {

		return "user_acl_list";
	}
	public String deptAuthIndex() {

		return "dept_acl_list";
	}
	public void deptTree() throws IOException {
		Party p = companyService.getCompany(0);
		PartyVOUpperCase vo = new PartyVOUpperCase(companyService.getCompany(0));
		try {
			String jsonString = JSONMapper.toJSON(vo).render(true);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(jsonString);

		} catch (MapperException e) {
			e.printStackTrace();
		}
	}
	public void roleTree() throws IOException {

		List<Role> rs = roleService.getAllRoles();

		List<RoleVO> roleVOs = new ArrayList<RoleVO>();
		for (Role r : rs) {
			RoleVO roleVO = new RoleVO(r);// 应该循环这个语句
			roleVOs.add(roleVO);
		}

		String jsonString = null;
		try {
			jsonString = JSONMapper.toJSON(roleVOs).render(true);

			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(jsonString);

		} catch (MapperException e) {
			e.printStackTrace();
		}

	}
	
	public void findActionResourceAcls() throws IOException {//查询数据生成树

//		List<ACL> acls = aclService.getAcls(principalType,principalId,"ActionResource");
//		List<AuthVO> authVOs = new ArrayList<AuthVO>();
//		
//		if(acls.size()!=0){
//			for (ACL acl : acls) {
//				ActionResource ar = resourceService.findActionResourceById(acl.getResourceId());
//				if(ar.getOpers()!=null){
//					Collection<ActionMethodOper> amos = ar.getOpers().values();
//					for (ActionMethodOper amo : amos) {
//						
//						AuthVO vo = new AuthVO();
//						vo.setOperIndex(amo.getOperIndex());
//						vo.setPermit(acl.isPermit(amo.getOperIndex()));
//						vo.setResourceId(acl.getResourceId());
//						vo.setExt(acl.isExt(amo.getOperIndex()));
//						authVOs.add(vo);
//					}
//				}
//			}
//			
//		}
		List<AuthVO> authVOs = new ArrayList<AuthVO>();
		authVOs = aclService.getAcls(principalType, principalId, "ActionResource");
		JSONUtils.toJSON(authVOs);
	}
	public void findMenuAcls() throws IOException {//查询数据生成树
//
//		List<ACL> acls = aclService.getAcls(principalType,principalId,"Menu");
//		List<AuthVO> authVOs = new ArrayList<AuthVO>();
//		
//		if(acls.size()!=0){
//			for (ACL acl : acls) {
//				AuthVO authVO = new AuthVO();
//				authVO.setResourceId(acl.getResourceId());
//				authVO.setOperIndex(0);
//				authVO.setPermit(acl.isPermit(0));
//				authVO.setExt(acl.isExt(0));
//				authVOs.add(authVO);
//			}
//			
//		}
		List<AuthVO> authVOs = new ArrayList<AuthVO>();
		authVOs = aclService.getAcls(principalType, principalId, "Menu");
		
		JSONUtils.toJSON(authVOs);
	}
	public void allMenuResourceTree() throws IOException {//查询数据生成树

		Menu m = menuService.getMenuById(topMenuId);
		MenuVO mvo = new MenuVO(m);
		JSONUtils.toJSON(mvo);
	}
	
	public void userList() {
		//只需要查出那些分配了登录帐号的人员即可，因为如果没有分配登录帐号，是无法
		//进行授权的，即授权的主体是User，而不是Person
		List user = userService.listUsersOnly(sSearch);
		Map map = new HashMap();
		map.put("aaData", user);
		JSONUtils.toJSON(map);
	}
	
	public void authMenu() {
		aclService.addOrUpdatePermission(principalType,principalId,"Menu",authvos);
		
	}
	public void authActionResource() {
		aclService.addOrUpdatePermission(principalType,principalId,"ActionResource",authvos);
		
	}


	public void setAuthvos(List<AuthVO> authvos) {
		this.authvos = authvos;
	}

	public List<AuthVO> getAuthvos() {
		return authvos;
	}

	public void setParam(List param) {
		this.param = param;
	}

	public List getParam() {
		return param;
	}
}
