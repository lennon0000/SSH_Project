package cn.com.leadfar.oa.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.leadfar.oa.dao.AclDao;
import cn.com.leadfar.oa.dao.MenuDao;
import cn.com.leadfar.oa.dao.ResourceDao;
import cn.com.leadfar.oa.dao.UserDao;
import cn.com.leadfar.oa.model.ACL;
import cn.com.leadfar.oa.model.ActionResource;
import cn.com.leadfar.oa.model.Menu;
import cn.com.leadfar.oa.model.Principal;
import cn.com.leadfar.oa.model.SysResource;
import cn.com.leadfar.oa.model.User;
import cn.com.leadfar.oa.partyVO.AuthVO;
import cn.com.leadfar.oa.service.AclService;

@Service
public class AclServiceImpl implements AclService {
	@Resource
	private AclDao aclDao;
	@Resource
	private MenuDao menuDao;
	@Resource
	private UserDao userDao;
	@Resource
	private ResourceDao resourceDao;
	
	@Override
	public void addOrUpdatePermission(String principalType, int principalId,
			String resourceType, List<AuthVO> authvos) {

		aclDao.delAcls(principalType, principalId, resourceType);

		if (authvos != null) {
			for (AuthVO authVO : authvos) {

				int operIndex = authVO.getOperIndex();
				int resourceId = authVO.getResourceId();
				boolean permit = authVO.isPermit();
				boolean ext = authVO.isExt();

				ACL acl = aclDao.getAcl(principalType, principalId,
						resourceType, resourceId);
				if (acl == null) {
					acl = new ACL();
					acl.setPrincipalType(principalType);
					acl.setPrincipalId(principalId);
					acl.setResourceType(resourceType);
					acl.setResourceId(authVO.getResourceId());
					acl.setPermission(operIndex, permit, ext);
					aclDao.save(acl);
				} else {
					acl.setPermission(operIndex, permit, ext);
					aclDao.update(acl);
				}

			}
		}

	}

	@Override
	public List<AuthVO> getAcls(String principalType, int principalId,
			String resourceType) {
		List<AuthVO> authVOs = new ArrayList<AuthVO>();

		// aclDao.getAcls(principalType,principalId,type);
		List<SysResource> sysResources = aclDao
				.getAllSysResources(resourceType);// [1]根据资源类型，查询出所有的资源（利用的是interface，通过继承实现接口，查询接口，这样可以比较灵活的查询出实现接口的类）,因为下边的查询中需要resourceId，所有需要把所有的resource查询出来
		// [4]构建principal对象
		Principal principal = aclDao.getPrincipal(principalType,principalId);// （之前设置的传来的principalType，就为类名，这是命名时的小技巧）

		// [2]要理解，最终结果是为了返回authVO集合，而查询每一条authVO，需要哪些参数，根据这个思路来凑
		for (SysResource res : sysResources) {

			// AuthVO vo = aclDao.getAcl(principalType, principalId,
			// resourceType, res.getResourceId());//[3]为了构建authVO对象，需要ext
			// operIndex permint resourceId这些属性，所以需要从t_acl表中进行查找
			// [4]上边的查询方法，查出来的只是一条acl记录，而且没有考虑到他是否继承或是其父类的授权情况，所以不能直接放入到authVOs集合中
			// 因为有的操作，可能他本身没有进行授权，但是如果继承了父类的话，就得分情况考虑，应该是，根据principal的ID和TYPE以及资源类型，查询出所有的acl，再结合每个acl的是否继承，及父类，来判断该principal对象，对应的sysResourceType中，所有acl的具体情况

			// [5]查找所有的acl，并考虑其是否继承，还有父类
			// 查出该resource的所有operId
			List<Integer> operIndexs = aclDao.getOperIds(resourceType,
					res.getResourceId());
			for (Integer operIndex : operIndexs) {
				AuthVO vo = getAcl(principal, resourceType,
						res.getResourceId(), operIndex);// 注意需要传这四个参数，为了获得一个authVO对象，需要知道他的principal和resource的type和id，以及他的operIndex，
				if (vo != null) {
					authVOs.add(vo);
				}
			}

		}
		//

		return authVOs;
	}
	public AuthVO getAcl(Principal principal, String resourceType,
			int resourceId, Integer operIndex) {
		AuthVO vo = null;
		ACL acl = aclDao.getAcl(principal.getPrincipalType(),
				principal.getPrincipalId(), resourceType, resourceId);
		if (acl != null && !acl.isExt(operIndex)) {// 这中情况下，acl中的值所得到的数据就是最后结果
													// (传入operIndex是为了检查该acl是否继承，是否允许)
			vo = new AuthVO();
			vo.setResourceId(resourceId);
			vo.setOperIndex(operIndex);
			vo.setExt(false);
			vo.setPermit(acl.isPermit(operIndex));
			return vo;
		}

		// 当出了上述情况时，其他情况都得考虑父类的acl情况，所以得获得所有的父类进行遍历，
		List<Principal> parents = principal.getParentPrincipal();// 在Principal
																	// interface中写get方法，其实现类具体实现

		if (parents != null) {
			for (Principal p : parents) {// 遍历每一个父类，针对每个父类分别查询其acl，这里用到递归算法
				AuthVO pvo = getAcl(p, resourceType, resourceId, operIndex);

				if (pvo != null && !pvo.isPermit()) {// 当父类不为空，且允许的话，则返回如下值
					vo = new AuthVO();
					vo.setResourceId(resourceId);
					vo.setOperIndex(operIndex);
					vo.setPermit(false);
					vo.setExt(true);
					return vo;// 这里有返回值，父类中只有有一个不同意的话，就返回不同意
				}
				if (pvo != null && pvo.isPermit()) {
					if (vo == null) {
						vo = new AuthVO();

						vo.setResourceId(resourceId);
						vo.setOperIndex(operIndex);
						vo.setPermit(true);
						vo.setExt(true);
					}// 注：这里没有return,只有当所有的父类都同意时，就运行下边的return vo
				}
			}
		}

		return vo;
	}

//	@Override
//	public List<Menu> getAllPermitMenu(int id) {
		// 这里边查出所有的menu，再结合针对该user的menu授权情况，删除或保留menu
		
		//先查出所有的menu，再进行判断
		//方法一：//这种通过查所有menu，然后再结合授权情况删除菜单不行，生成的最后结果是之气的每个子类，都成为顶级菜单
//				List<Menu> dbMenus = menuDao.getAllMenu();
//				for (Iterator<Menu> iterator = dbMenus.iterator(); iterator.hasNext();) {
//					Menu menu = iterator.next();
//					AuthVO vo = getAcl(userDao.getUserById(id), "Menu", menu.getId(), 0);
//					if(vo==null || !vo.isPermit()){
//						iterator.remove();
//					}
//					
//				}
//		return dbMenus;
//	}
	@Override
	public List<Menu> getAllPermitMenu(int id) {
		//方法二：之前第一种方法我想的是获得所有的后，再根据授权一条一条的删除，返回去的是所有授权了的menu对象集合，第二种方法返回的是顶级菜单的集合，后者可以生成正常的树，在后者中，通过先判断顶级菜单，没有授权就删除，有的话再【判断子菜单，子菜单没有的话也删除，这样在返回的顶级菜单集合中，为所有授权了的顶级菜单，并且子菜单也均授权
		List<Menu> dbTopMenus = menuDao.getTopMenuList();//获得所有的顶级菜单
		
		delDenyMenu(dbTopMenus,id);
		
		
		return dbTopMenus;
	}

	private void delDenyMenu(List<Menu> dbTopMenus, int id) {
		for (Iterator<Menu> iterator = dbTopMenus.iterator(); iterator.hasNext();) {
			Menu menu = iterator.next();//需要注意的是不能用最后一种遍历集合的方法，那种不能进行删除操作
			System.out.println(menu.getId());
			AuthVO vo = getAcl(userDao.getUserById(id), "Menu", menu.getId(), 0);
			if(vo==null || !vo.isPermit()){
				iterator.remove();//顶级菜单没授权的话，直接删除
			}else{//顶级菜单授权的话，再查询子菜单
				Set<Menu> childrenSet = menu.getChildren();//之前是直接改为了Set类型，但是这样生成的菜单老是乱序
				List<Menu> childrenList = new ArrayList<Menu>();
				for (Menu m : childrenSet) {
					childrenList.add(m);
				}
				delDenyMenu(childrenList, id);
			}
			
		}
		
		
		
	}
	@Override
	public boolean operHasPermit(int userId,String resourceSn,String oper){//根据用户id，资源类型，查询是否有该操作的权利（权限操作和组织机构操作中的增删改查操作）
		//根据之前写好的判断主体对于某操作是否有权利的方法，来查询，根据需要传入的值，然后用现有的数据进行拼凑查找
		User user = userDao.getById(User.class, userId);
		ActionResource r = resourceDao.findActionResourceBySn(resourceSn);
		int operIndex = r.getOperIndexByOperSn(oper);//注：这里通过operSn来获得operId的方法不同与之前从数据库进行查找，而是通过在Resource类中写个方法，这样就不用在数据库中进行查找了
		AuthVO vo = getAcl(user, r.getResourceType(), r.getId(), operIndex);
		if(vo!=null && vo.isPermit()){
			return true;
		}else{
			return false;
			}
		
	}
}
