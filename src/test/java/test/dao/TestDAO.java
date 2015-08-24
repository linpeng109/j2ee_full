package test.dao;

import com.cn.dao.entity.UserBase;
import com.cn.dao.util.HibernateDAO;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import test.TestBase;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by linpeng109 on 15-8-18.
 */
public class TestDAO extends TestBase {

    private final Logger logger = Logger.getLogger(TestDAO.class);

    @Resource
    public HibernateDAO hibernateDAO;

    @Test
    @Transactional
    public void testHibernateDAOCountUserBase() {
        int count = hibernateDAO.count(UserBase.class, true);
        logger.debug(String.format("The count of UserBase is [%s].", count));
    }

    @Test
    @Transactional
    public void testHibernateDAOListByAll() {
        List<UserBase> list = hibernateDAO.listByAll(UserBase.class, true);
        int size = list.size();
        logger.debug(String.format("The size of UserBase is [%s]", size));
        for (UserBase user : list) {
            logger.debug(user.getUserName());
        }
    }


}
