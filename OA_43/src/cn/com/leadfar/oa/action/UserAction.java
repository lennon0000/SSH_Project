package cn.com.leadfar.oa.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.annotations.Oper;
import cn.com.leadfar.oa.annotations.Res;
import cn.com.leadfar.oa.model.Person;
import cn.com.leadfar.oa.model.Role;
import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.model.UserRoles;
import cn.com.leadfar.oa.partyVO.PagerVO;
import cn.com.leadfar.oa.service.RoleService;
import cn.com.leadfar.oa.service.UserService;
import cn.com.leadfar.oa.utils.SystemContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.sdicons.json.mapper.JSONMapper;

@Component
@Scope("prototype")
@Res(name="用户操作",sn="user",orderNumber=8,parentSn="security")
public class UserAction implements ModelDriven {
	private User user;
	private String sSearch;
	private List<Integer> roleIds;
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;

	public String getSSearch() {
		return sSearch;
	}

	public void setSSearch(String sSearch) {
		this.sSearch = sSearch;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public Object getModel() {
		if (user == null) {
			user = new User();
		}
		return user;
	}
	@Oper
	public String listUsers() {

		return "user_list";
	}
	@Oper
	public String del() {
		userService.del(user);
		return "update_success";
	}	
	@Oper
	public String addInput() {
		// 添加前先得把所有的role查出来，显示在addInput.jsp中
		// 保存user时，需要链接上person对象，所以需要在上个页面(userList)传递过来person的id
		int personId = user.getPerson().getId();
		user = userService.getUserById(user.getPerson().getId());
		if(user==null){
			user = new User();
			Person p = new Person();
			
			p.setId(personId);
			user.setPerson(p);
			List<Role> roles = roleService.getAllRoles();
			ActionContext.getContext().put("roles", roles);
			return "add_input";
		}else{
			ActionContext.getContext().put("addInfor", "已有用户，不能再次添加用户,如需要更改用户数据，请点击更新用户！");
			return "user_list";
		}
		
	}
	@Oper
	public String updateInput() {
		int personId = user.getPerson().getId();
		user = userService.getUserById(user.getPerson().getId());// 需要注意的是，在这个一对一的关联中，person的id就是user的id，要利用这种关系查找user对象
		// roleIds = new ArrayList<Integer>();      
		//  或是利用这个条件，在jsp传id时就传id，则这边直接获得user  id
		if(user==null){
			user = new User();
			Person p = new Person();
			p.setId(personId);
			user.setPerson(p);
			List<Role> roles = roleService.getAllRoles();
			ActionContext.getContext().put("roles", roles);
			return "add_input";
		}else{
			Set<UserRoles> userRoles = user.getRoles();

			List<Role> roles = roleService.getAllRoles();
			List<Role> rs = new ArrayList<Role>();
			for (Role role : roles) {
				for (UserRoles userRole : userRoles) {
					if (role.getId() == userRole.getRole().getId()) {
						role.setStatus("selected");
						System.out.println("select = \"selected\"");
						
					}
				}
			}
			ActionContext.getContext().put("roles", roles);
			for (Role role : roles) {
				System.out.println(role.getStatus());
			}
			return "update_input";
		}
		
	}
	@Oper
	public String add() {

		userService.save(user, roleIds);

		return "update_success";
	}
	@Oper
	public String update() {
		userService.update(user, roleIds);
		return "update_success";
	}
	@Oper
	public void list() {
		PagerVO pagerVOs = new PagerVO();
		userService.listUsers(pagerVOs, sSearch);

		PagerVO pv2 = new PagerVO();
		pv2.setTotal(pagerVOs.getTotal());

		int total = SystemContext.getOffset() + SystemContext.getPagesize();
		if (total > pagerVOs.getTotal()) {
			total = pagerVOs.getTotal();
		}

		pv2.setDatas(pagerVOs.getDatas().subList(SystemContext.getOffset(),
				total));
		// 页面中需要展现的数据为二维数组，不是person list，数组包括以下三个属性
		Map vo = new HashMap();// 将pagerVO对象，转换为ajaxjason需要的对象格式
		vo.put("iTotalRecords", pv2.getTotal());
		vo.put("iTotalDisplayRecords", pv2.getTotal());
		vo.put("aaData", pv2.getDatas());

		// 因为用ajaxjason获得数据进行输出及控制时，操作的是JSON格式的数据，所以得把数据转换为JSON格式
		try {// 将vo对象转换为JSON数据格式
			String jsonString = JSONMapper.toJSON(vo).render(false);
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			ServletActionContext.getResponse().getWriter().print(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setRoleIds(List<Integer> roleIds) {
		this.roleIds = roleIds;
	}

	public List<Integer> getRoleIds() {
		return roleIds;
	}

}
