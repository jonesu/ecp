package com.ecp.core.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ecp.core.basic.Page;

@SuppressWarnings("serial")
public class CommonServiceSupport implements Serializable {
	
	/**
	 * 获取带事务的entityManager
	 */
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据ID获取对象(非延迟加载)
	 */
	public Object get(Class clazz,Object id) throws Exception {
		return entityManager.find(clazz, id);
	}
	
	/**
	 * 根据ID查询对象(延迟加载)
	 * 此方法在事务内使用否则会出现懒加载异常
	 */
	public Object load(Class clazz,Object id) throws Exception {
		
		return entityManager.getReference(clazz, id);
	}
	
	/**
	 * 根据ID列表获取对象列表
	 * @param ids
	 * @return
	 */
	public List getListByIds(Class clazz,List ids) throws Exception {
		
		String jpql = " from " + clazz.getName() + " as model where model.id in(?1)";
		
		return entityManager.createQuery(jpql).setParameter(1,ids).getResultList();
	}
	
	/**
	 * 根据属性查询唯一对象
	 */
	public Object getUniquer(Class clazz,String propertyName, Object value) throws Exception {
		
		String jpql = " from " + clazz.getName() + " as model where model." + propertyName + " = ?1";
		
		return entityManager.createQuery(jpql).setParameter(1, value).getSingleResult();
	}
	
	/**
	 * 根据jpql查询唯一对象
	 */
	public Object getUniquerByJPQL(String jpql, Object... args ) throws Exception {
		

		Query query = entityManager.createQuery(jpql);
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}
		
		return query.getSingleResult();
	}
	
	/**
	 * 根据sql查询唯一对象
	 */
	public Object getUniquerBySQL(String sql, Object... args ) throws Exception {
		

		Query query = entityManager.createNativeQuery(sql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}
		
		return query.getSingleResult();
	}
	
	/**
	 * 获取所有对象列表
	 * @param clazz
	 * @return
	 */
	public List getAll(Class clazz) throws Exception {
		
		String jpql = "from " + clazz.getName() + " as model ";
		
		return entityManager.createQuery(jpql).getResultList();
	}
	
	/**
	 * 持久化对象
	 * @param entity
	 * @return
	 */
	public Object save(Object entity) throws Exception {
		
		entityManager.persist(entity);
		
		return entity;
	}
	
	/**
	 * 批量持久化对象
	 * @param list
	 */
	public void save(List list) throws Exception {

		for(int i = 0;i<list.size();i++){
			Object entity = list.get(i);
			entityManager.persist(entity);
			
			if(i%100 == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
	}
	
	/**
	 * 更新对象
	 * @param entity
	 */
	public void saveOrUpdate(Object entity) throws Exception {

		entityManager.flush();
		entityManager.clear();
		entityManager.merge(entity);
		entityManager.flush();
	}
	
	/**
	 * 批量更新对象
	 * @param list
	 */
	public void saveOrUpdate(List list) throws Exception {

		for(int i = 0;i<list.size();i++){
			
			Object entity = list.get(i);
			
			entityManager.merge(entity);
			
			if(i%100 == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
		
	}
	
	/**
	 * 根据实体删除对象
	 * @param entity
	 */
	public void delete(Object entity) throws Exception {
		entityManager.remove(entity);
	}
	
	/**
	 * 根据ID删除对象
	 * @param id
	 */
	public void delete(Class clazz,Object id) throws Exception {
		
		Object entity = load(clazz,id);
		
		entityManager.remove(entity);
	}
	
	/**
	 * 根据ID批量删除对象
	 * @param clazz
	 * @param ids
	 */
	public void delete(Class clazz,Object[] ids) throws Exception {

		for (Object id : ids) {
			Object entity = load(clazz,id);
			entityManager.remove(entity);
		}
		
	}
	
	/**
	 * 根据属性查询对象列表
	 * @param clazz
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public List findByProperty(Class clazz,String propertyName, Object value) throws Exception {

		String jpql = "from " + clazz.getName() + " as model where model." + propertyName + " = ?1";
		
		return entityManager.createQuery(jpql).getResultList();
	}
	
	
	
	/**
	 * SQL分页查询
	 * @param pageNum
	 * @param pageSize
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception 
	 */
	public Page getPageBySQL(Page page, String sql,Object... args) throws Exception {
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}

		query.setFirstResult((page.getPageNo() -1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		
		
		
		List list = query.getResultList();
		
		String countSQL = " select count(*) count from ("+sql+") ";
		
		Query countQuery = entityManager.createNativeQuery(countSQL);
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				countQuery.setParameter(i,args[i-1]);
			}
		}
		
		Long count = (Long) countQuery.getSingleResult();
		
		page.setDatas(list);
		page.setTotalCount(count.intValue());
		
		return page;
	}
	
	/**
	 * 根据JPQL语句查询分页
	 * @param page
	 * @param jpql
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public Page getPageByJPQL(Page page, String jpql,Object... args) throws Exception {
		
	
		Query query = entityManager.createQuery(jpql);
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}

		
		query.setFirstResult((page.getPageNo() -1) * page.getPageNo());
		query.setMaxResults(page.getPageSize());
		
		
		List list = query.getResultList();
		
		//构建查询总数JPQL
		String countJpql = "select count(*) " + jpql.substring(jpql.indexOf("from"));
		
		Query countQuery = entityManager.createQuery(countJpql);
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				countQuery.setParameter(i,args[i-1]);
			}
		}
		
		Long count = (Long) countQuery.getSingleResult();
		
		page.setDatas(list);
		page.setTotalCount(count.intValue());
		
		return page;
		
	}
	
	/**
	 * 执行SQL
	 * @param sql
	 * @param args
	 * @throws Exception
	 */
	public void executeSQL(String sql,Object... args) throws Exception {
		
		Query query = entityManager.createNativeQuery(sql);
		
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}
		
		query.executeUpdate();
		
	}
	
	/**
	 * 根据SQL查询列表
	 * @param sql
	 * @param args
	 * @return
	 * @throws Exception 
	 */
	public List getListBySQL(Page page, String sql,Object... args) throws Exception {
		
		Query query = entityManager.createNativeQuery(sql);
		
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); 
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}

		query.setFirstResult((page.getPageNo() -1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		
		
		
		List list = query.getResultList();
		
		
		return list;
	}
	
	/**
	 * 根据JPQL语句查询列表
	 * @param args
	 * @return
	 * @throws Exception
	 */
	public List getListByJPQL(String jpql,Object... args) throws Exception {
		
	
		Query query = entityManager.createQuery(jpql);
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}

		List list = query.getResultList();

		return list;
		
	}
	
	/**
	 * 执行JPQL
	 * @param JPQL
	 * @param args
	 * @throws Exception
	 */
	public void executeJPQL(String jpql,Object... args) throws Exception {
		
		Query query = entityManager.createQuery(jpql);
		
		
		if(args != null && args.length > 0){
			for(int i=1;i<=args.length;i++){
				query.setParameter(i,args[i-1]);
			}
		}
		
		query.executeUpdate();
		
	}
	
	public void flush() {
		
		entityManager.flush();
		
	}

	public void clear() {
		entityManager.clear();
		
	}
	
	/**
	 * 1.数据库模板操作类
	 * 2.一般情况下不用
	 * @return
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	
	
}
