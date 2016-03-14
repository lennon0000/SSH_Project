package cn.com.leadfar.oa.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import cn.com.leadfar.oa.dao.BaseDao;
import cn.com.leadfar.oa.partyVO.PagerVO;
import cn.com.leadfar.oa.utils.SystemContext;
@Component("baseDao")
public class BaseDaoImpl implements BaseDao {
	@Resource
	protected SessionFactory sessionFactory; 
	
	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void save(Object object) {
		getSession().save(object);
		
	}
	@Override
	public void update(Object object) {
		getSession().update(object);
		
	}
	@Override
	public void del(Object object) {
		getSession().delete(object);
		
	}
	@Override
	public <T> T getById(Class<T> objectType, int id) {
		return (T) getSession().get(objectType, id);
	}
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAll(Class<T> objectType) {
		return (List<T>) getSession().createQuery("from "+ objectType.getSimpleName()).list();
	}
	public PagerVO searchPaginated(String hql){//当没有查询条件时，只通过查询语句
		return searchPaginated(hql, null);
	}
	
	public PagerVO searchPaginated(String hql,Object param){//当只有一个查询条件时，将传递过来的一个条件，设置到数组中
		return searchPaginated(hql, new Object[]{param});
	}
	
	
	public PagerVO searchPaginated(String hql,Object[] params){
		//最为根本的部分，其他的均是为了适应不同查询条件，重写的方法，这样的好处是，在这里重写了多种方法后，
		//在继承了baseDaoImpl的dao方法中，调用这些方法更方便
		//查询条件包括起始页，页面大小，以及其他的查询条件构成的数组
		String countHql = getCountHql(hql);
		Query query = getSession().createQuery(countHql);
		if(params != null && params.length > 0){//遍历设置查询条件
			for(int i=0; i<params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		int total = ((Long)query.uniqueResult()).intValue();//查询获得的是总记录数（需要在查询条件的基础上）
		
		query = getSession().createQuery(hql);
		if(params != null && params.length > 0){
			for(int i=0; i<params.length; i++){
				query.setParameter(i, params[i]);
			}
		}
		//query.setFirstResult(offset);
		//query.setMaxResults(pagesize);
		List datas = query.list();//查询获得的是数据，即person对象的id，sex。phonenumber等构成的集合（需要在查询条件的基础上）
		
		PagerVO vo = new PagerVO();//将查询得到的数据设置到PagerVo对象中返回
		vo.setDatas(datas);
		vo.setTotal(total);
		return vo;
		
	}
	
	/**
	 * 根据查询语句，得到查询总记录数的查询语句
	 * 比如：
	 * select p.id,p.name,p.sex,p.phone from Person p where p.parent.id = ?
	 * 应该得到：
	 * select count(*) from Person p where p.parent.id = ?
	 * @param hql
	 * @return
	 */
	private String getCountHql(String hql){
		int index = hql.indexOf("from");
		if(index == -1){
			throw new RuntimeException("非法的查询语句【"+hql+"】");
		}
		return "select count(*) "+hql.substring(index);
	}
	
	
}
