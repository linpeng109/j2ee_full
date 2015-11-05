package com.cn.hibernate.interceptor;

import com.cn.hibernate.entity.UserBase;
import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;


public class UserBaseSaveOrUpdateInterceptor extends EmptyInterceptor {
    private final Logger logger = Logger.getLogger(UserBaseSaveOrUpdateInterceptor.class);

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state,String[] propertyNames, Type[] types) {
        if (entity instanceof UserBase) {
            String message = "user=" + ((UserBase) entity).getUserName()
                    + " will be created!";
            logger.debug(String.format("SaveOrUpdateInterceptor is running,The data is [%s]", message));
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }

}
