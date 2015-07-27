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
 * ͨ�����ݲ�ѯ����
 * 
 * @author Administrator
 * 
 */
public class HibernateDAO {
	/**
	 * ��־
	 */
	public Logger logger = Logger.getLogger(HibernateDAO.class);

	/**
	 * ����ע��session����ʵ��
	 */
	public SessionFactory sessionFactory;

	/**
	 * ���캯��
	 */
	public HibernateDAO() {
		super();
		sessionFactory = this.getSessionFactory();
	}

	/**
	 * ������������ѯ
	 * 
	 * @param clazz��ѯʵ����
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
	 * ������������ѯ
	 * 
	 * @param clazz��ѯʵ����
	 * @param criterion��ѯ����
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
	 * ȫ����ѯ
	 * 
	 * @param clazz��ѯʵ����
	 * @param isCached�Ƿ񻺴�
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
	 * ������ѯ
	 * 
	 * @param clazz��ѯʵ����
	 * @param criterion��ѯ����
	 * @param isCached�Ƿ񻺴�
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
	 * �ֶβ�ѯ
	 * 
	 * @param clazz��ѯʵ����
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
	 * ����id��ѯ
	 * 
	 * @param clazz��ѯʵ����
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
	 * ԭ���ݲ�ѯ
	 * 
	 * @param clazz��ѯʵ����
	 * @return
	 */
	public <T> ClassMetadata listByMetaData(Class<T> clazz) {
		ClassMetadata result = null;
		Session session = sessionFactory.getCurrentSession();
		result = session.getSessionFactory().getClassMetadata(clazz);
		return result;
	}

	/**
	 * ��ҳ��ѯ
	 * 
	 * @param clazz��ѯʵ����
	 * @param pageSizeÿҳ������
	 * @param pageCurrent��ǰҳ
	 * @param isCached�Ƿ񻺴�
	 * @return
	 */
	public <T> List<T> listByPage(Class<T> clazz, int pageSize,
			int pageCurrent, boolean isCached) {
		/**
		 * ��ȡ�Ự
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * ������ѯ
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
	 * ��ҳ������ѯ
	 * 
	 * @param clazzʵ����
	 * @param criterion��ѯ����
	 * @param pageSizeÿҳ������
	 * @param pageCurrent��ǰҳ
	 * @param isCached�Ƿ񻺴�
	 * @return
	 */
	public <T> List<T> listByPageByCriteria(Class<T> clazz,
			Criterion criterion, int pageSize, int pageCurrent, boolean isCached) {
		/**
		 * ��ȡ�Ự
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * ������ѯ
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
	 * ��ҳ����ģ����ѯ
	 * 
	 * @param clazzʵ����
	 * @param detachedCriteria���߲�ѯ
	 * @param pageSizeÿҳ������
	 * @param pageCurrent��ǰҳ
	 * @param isCached�Ƿ񻺴�
	 * @return
	 */
	public <T> List<T> listByPageByDetachedCriteria(
			DetachedCriteria detachedCriteria, int pageSize, int pageCurrent,
			boolean isCached) {
		/**
		 * ��ȡ�Ự
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * ������ѯ
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
	 * ʵ����ѯ
	 * 
	 * @param entity��ѯʵ��
	 * @param pageSizeÿҳ������
	 * @param pageCurrent��ǰҳ
	 * @param isCached�Ƿ񻺴�
	 * @return
	 */
	public <T> List<T> listByPageByExample(Example example, int pageSize,
			int pageCurrent, boolean isCached) {
		/**
		 * �����Ự
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * ������ѯ
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
	 * HQL����ѯ
	 * 
	 * @param <T>
	 * 
	 * @param hql��ѯ�ַ���
	 * @param pageSizeÿҳ������
	 * @param pageCurrent��ǰҳ
	 * @param isCached�Ƿ񻺴�
	 * @return
	 */
	public <T> List<T> listByPageByHQL(String hql, int pageSize,
			int pageCurrent, boolean isCached) {
		/**
		 * ��ȡ�Ự
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * ��ȡ��ѯ
		 */
		Query query = session.createQuery(hql);
		query.setFirstResult(pageCurrent * pageSize);
		query.setMaxResults(pageSize);
		query.setCacheable(isCached);
		/**
		 * ��ȡ�б�
		 */
		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		return result;
	}

	/**
	 * ���ϲ�ѯ
	 * 
	 * @param clazz
	 * @param projection
	 * @param pageSizeÿҳ������
	 * @param pageCurrent��ǰҳ
	 * @param isCached�Ƿ񻺴�
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
		 * �������
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
		 * �����������
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
	 * �����ѯ
	 * 
	 * @param clazz��ѯʵ����
	 * @param projectionͶӰ��ѯ
	 * @param pageSizeÿҳ������
	 * @param pageCurrent��ǰҳ
	 * @param isCached�Ƿ񻺴�
	 * @return
	 */
	public <T> List<T> listByProjection(Class<?> clazz, Projection projection,
			int pageSize, int pageCurrent, boolean isCached) {
		/**
		 * ��ȡ�Ự
		 */
		Session session = sessionFactory.getCurrentSession();
		/**
		 * ͶӰ��ѯ
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
	 * ɾ������
	 * 
	 * @param entity��ѯʵ��
	 * @return
	 */
	public String removeByEntity(final Object entity) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(entity);
		String result = "success";
		return result;
	}

	/**
	 * ����Id�h��
	 * 
	 * @param clazzʵ����
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
	 * ͨ��entity������������
	 * 
	 * @param entityʵ����ʵ��
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
	 * ͨ��SQL��䱣����������
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
	 * ͨ��HQL��䱣����������
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
	 * SQL����ѯ
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
	 * HQL����ѯ
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
