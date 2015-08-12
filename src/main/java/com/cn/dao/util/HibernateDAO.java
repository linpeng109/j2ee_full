package com.cn.dao.util;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.Transformers;

/**
 * 通用数据查询对象
 * 
 * @author Administrator
 * 
 */
public class HibernateDAO {
	/**
	 * 日志
	 */
	public Logger logger = Logger.getLogger(HibernateDAO.class);

	/**
	 * 依赖注入session工厂实例
	 */
	public SessionFactory sessionFactory;

	/**
	 * 构造函数
	 */
	public HibernateDAO() {
		super();
		sessionFactory = this.getSessionFactory();
	}

	/**
	 * 无条件计数查询
	 * 
	 * @param clazz查询实体类
	 * @param isCached
	 * @return
	 */
	public <T> int count(Class<T> clazz, boolean isCached) {
		int result = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		criteria.setProjection(Projections.rowCount());
		result = Integer.parseInt(criteria.uniqueResult().toString());
		return result;
	}

	/**
	 * 有条件计数查询
	 * 
	 * @param clazz查询实体类
	 * @param criterion查询条件
	 * @return
	 */
	public <T> int countByCriteria(Class<T> clazz, Criterion criterion,
			boolean isCached) {
		int result = 0;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		criteria.add(criterion);
		criteria.setProjection(Projections.rowCount());
		result = Integer.parseInt(criteria.uniqueResult().toString());
		return result;
	}

	/**
	 * 全部查询
	 * 
	 * @param clazz查询实体类
	 * @param isCached是否缓存
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> listByAll(Class<T> clazz, boolean isCached) {
		List<T> result = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		result = criteria.list();
		return result;
	}

	/**
	 * 条件查询
	 * 
	 * @param clazz查询实体类
	 * @param criterion查询条件
	 * @param isCached是否缓存
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> listByCriteria(Class<T> clazz, Criterion criterion,
			boolean isCached) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		criteria.add(criterion);
		List<T> result = criteria.list();
		return result;
	}

	/**
	 * 字段查询
	 * 
	 * @param clazz查询实体类
	 * @return
	 */
	public <T> String[] listByFieldNames(Class<T> clazz) {
		String[] result = null;
		Session session = sessionFactory.getCurrentSession();
		ClassMetadata classMetadata = session.getSessionFactory()
				.getClassMetadata(clazz);
		String[] temp = classMetadata.getPropertyNames();
		String[] strings = new String[temp.length + 1];
		strings[0] = classMetadata.getIdentifierPropertyName();
		for (int i = 0; i < temp.length; ++i) {
			strings[i + 1] = temp[i];
		}
		result = strings;
		return result;
	}

	/**
	 * 根据id查询
	 * 
	 * @param clazz查询实体类
	 * @param id
	 * @param isCached
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T listById(Class<T> clazz, Object id, boolean isCached) {
		T result = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		criteria.add(Restrictions.idEq(id));
		result = ((T) criteria.uniqueResult());
		return result;
	}

	/**
	 * 原数据查询
	 * 
	 * @param clazz查询实体类
	 * @return
	 */
	public <T> ClassMetadata listByMetaData(Class<T> clazz) {
		ClassMetadata result = null;
		Session session = sessionFactory.getCurrentSession();
		result = session.getSessionFactory().getClassMetadata(clazz);
		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param clazz查询实体类
	 * @param pageSize每页数据量
	 * @param pageCurrent当前页
	 * @param isCached是否缓存
	 * @return
	 */
	public <T> List<T> listByPage(Class<T> clazz, int pageSize,
			int pageCurrent, boolean isCached) {
		/**
		 * 获取会话
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * 条件查询
		 */
		Criteria criteria = session.createCriteria(clazz);
		criteria.setFirstResult(pageCurrent * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.setCacheable(isCached);
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		return result;
	}

	/**
	 * 分页条件查询
	 * 
	 * @param clazz实体类
	 * @param criterion查询条件
	 * @param pageSize每页数据量
	 * @param pageCurrent当前页
	 * @param isCached是否缓存
	 * @return
	 */
	public <T> List<T> listByPageByCriteria(Class<T> clazz,
			Criterion criterion, int pageSize, int pageCurrent, boolean isCached) {
		/**
		 * 获取会话
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * 条件查询
		 */
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		criteria.add(criterion);
		criteria.setFirstResult(pageCurrent * pageSize);
		criteria.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		return result;
	}

	/**
	 * 分页离线模糊查询
	 * 
	 * @param clazz实体类
	 * @param detachedCriteria离线查询
	 * @param pageSize每页数据量
	 * @param pageCurrent当前页
	 * @param isCached是否缓存
	 * @return
	 */
	public <T> List<T> listByPageByDetachedCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int pageCurrent,
			boolean isCached) {
		/**
		 * 获取会话
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * 条件查询
		 */
		Criteria criteria = detachedCriteria.getExecutableCriteria(session);

		criteria.setCacheable(isCached);
		criteria.setFirstResult(pageCurrent * pageSize);
		criteria.setMaxResults(pageSize);
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		return result;
	}

	/**
	 * 实例查询
	 * 
	 * @param entity查询实例
	 * @param pageSize每页数据量
	 * @param pageCurrent当前页
	 * @param isCached是否缓存
	 * @return
	 */
	public <T> List<T> listByPageByExample(Example example, int pageSize,
			int pageCurrent, boolean isCached) {
		/**
		 * 创建会话
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * 条件查询
		 */
		Criteria criteria = session.createCriteria(example.getClass());
		criteria.setCacheable(isCached);
		criteria.setFirstResult(pageCurrent * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.add(example);
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		return result;
	}

	/**
	 * HQL语句查询
	 * 
	 * @param <T>
	 * 
	 * @param hql查询字符串
	 * @param pageSize每页数据量
	 * @param pageCurrent当前页
	 * @param isCached是否缓存
	 * @return
	 */
	public <T> List<T> listByPageByHQL(String hql, int pageSize,
			int pageCurrent, boolean isCached) {
		/**
		 * 获取会话
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * 获取查询
		 */
		Query query = session.createQuery(hql);
		query.setFirstResult(pageCurrent * pageSize);
		query.setMaxResults(pageSize);
		query.setCacheable(isCached);
		/**
		 * 获取列表
		 */
		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		return result;
	}

	/**
	 * 联合查询
	 * 
	 * @param clazz
	 * @param projection
	 * @param pageSize每页数据量
	 * @param pageCurrent当前页
	 * @param isCached是否缓存
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> listByPageByProjection(Class<T> clazz,
			String[] propertyNames, String[] operators, Object[] values,
			String[] orderProperties, boolean[] descFlags,
			boolean distinctFlag, int pageSize, int currentPageNum,
			boolean isCached) {
		List<T> result = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		criteria.setFirstResult(currentPageNum * pageSize);
		criteria.setMaxResults(pageSize);
		int size = propertyNames.length;

		/**
		 * 参数检测
		 */
		if (operators.length != size || values.length == size
				|| orderProperties.length != descFlags.length) {
			try {
				throw new Exception("Parameter Error!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 处理操作符号
		 */
		for (int i = 0; i < size; ++i) {
			if (operators[i].equals(QueryParam.OPERATOR_EQ)) {
				criteria.add(Restrictions.eq(propertyNames[i], values[i]));
			}
			if (operators[i].equals(QueryParam.OPERATOR_GE)) {
				criteria.add(Restrictions.ge(propertyNames[i], values[i]));
			}

			criteria.addOrder(Order.desc(propertyNames[i]));
		}

		ProjectionList projectionList = Projections.projectionList();

		criteria.setProjection(projectionList);
		result = criteria.list();
		return result;
	}

	/**
	 * 报表查询
	 * 
	 * @param clazz查询实体类
	 * @param projection投影查询
	 * @param pageSize每页数据量
	 * @param pageCurrent当前页
	 * @param isCached是否缓存
	 * @return
	 */
	public <T> List<T> listByProjection(Class<?> clazz, Projection projection,
			int pageSize, int pageCurrent, boolean isCached) {
		/**
		 * 获取会话
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * 投影查询
		 */
		Criteria criteria = session.createCriteria(clazz);
		criteria.setCacheable(isCached);
		criteria.setFirstResult(pageCurrent * pageSize);
		criteria.setMaxResults(pageSize);
		criteria.setProjection(projection);
		@SuppressWarnings("unchecked")
		List<T> result = criteria.list();
		return result;
	}

	/**
	 * 删除对象
	 * 
	 * @param entity查询实体
	 * @return
	 */
	public String removeByEntity(final Object entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
		String result = "success";
		return result;
	}

	/**
	 * 根据Id刪除
	 * 
	 * @param clazz实体类
	 * @param id
	 */
	public <T> String removeById(Class<T> clazz, Object id) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(clazz);
		criteria.add(Restrictions.idEq(id));
		session.delete(criteria.uniqueResult());
		String result = "success";
		return result;
	}

	/**
	 * 通过entity插入或更新数据
	 * 
	 * @param entity实体类实例
	 * @return
	 */
	public <T> String saveOrUpdate(T entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
		session.flush();
		String result = "success";
		return result;
	}

	/**
	 * 通过SQL语句保存或更新数据
	 * 
	 * @param sqlString
	 */
	public int saveOrUpdateBySQL(String saveOrUpdateSqlString) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery sqlQuery = session.createSQLQuery(saveOrUpdateSqlString);
		int result = sqlQuery.executeUpdate();
		return result;
	}

	/**
	 * 通过HQL语句保存或更新数据
	 * 
	 * @param saveOrUpdateHqlString
	 * @return
	 */
	public int saveOrUpdateByHQL(String saveOrUpdateHqlString) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(saveOrUpdateHqlString);
		int result = query.executeUpdate();
		return result;
	}

	/**
	 * SQL语句查询
	 * 
	 * @param listSqlString
	 * @return
	 */
	public List<?> listByPageBySQL(String listSqlString, int pageSize,
			int pageCurrent, boolean isCached) {
		Session sesson = sessionFactory.getCurrentSession();
		SQLQuery query = sesson.createSQLQuery(listSqlString);
		query.setFirstResult(pageCurrent * pageSize);
		query.setMaxResults(pageSize);
		query.setCacheable(false);
		List<?> list = query.setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP).list();
		return list;
	}

	/**
	 * HQL语句查询
	 * 
	 * @param listHqlString
	 * @return
	 */
	public List<?> listByHQL(String listHqlString) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(listHqlString);
		List<?> list = query.list();
		return list;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}