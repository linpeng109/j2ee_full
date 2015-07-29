package com.cn.dao.interceptor;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.cn.entity.UserBase;


@SuppressWarnings("serial")
public class UserBaseSaveOrUpdateInterceptor extends EmptyInterceptor {
	private static Logger logger = Logger
			.getLogger(UserBaseSaveOrUpdateInterceptor.class);

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		if (entity instanceof UserBase) {
			String message = ((UserBase) entity).getUserName()
					+ " will be created!";
			logger.debug(message);
		}
		return super.onSave(entity, id, state, propertyNames, types);
	}
	
	
}
