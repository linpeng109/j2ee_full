package test.dao;

import com.cn.dao.entity.UserBase;
import com.cn.dao.util.HibernateDAO;
import org.apache.log4j.Logger;
import org.junit.Test;
import test.TestBase;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by linpeng109 on 15-8-18.
 */
public class TestDAO extends TestBase {
    @Resource
    public HibernateDAO hibernateDAO;

    private final Logger logger = Logger.getLogger(TestDAO.class);

    @Test
    public void testHibernateDAOListByAll() {
        List<UserBase> list = hibernateDAO.listByAll(UserBase.class, true);
        for (UserBase user : list) {
            logger.debug(user.getUserName());
        }
    }

}
