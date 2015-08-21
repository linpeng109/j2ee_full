package test.dao;

import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import com.cn.dao.entity.Authority;
import com.cn.dao.entity.UserBase;
import com.cn.dao.entity.UserBase_Authority;
import com.cn.dao.util.HibernateDAO;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;
import test.TestBase;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by linpeng109 on 15-8-18.
 */
public class TestDAO extends TestBase {

    private final Logger logger = Logger.getLogger(TestDAO.class);

    @Resource
    public HibernateDAO hibernateDAO;

    //@Test
    @Transactional(rollbackFor = Exception.class, value = "transactionManager")
    public void testHibernateDAOInsertAbcd() {
        List<Authority> authorityList = hibernateDAO.listByAll(Authority.class,
                true);
        /**
         * abcd是全权限用户
         */
        UserBase abcd = new UserBase();
        abcd.setUserName("abcd");
        abcd.setPassWord("abcd");
        abcd.setCreateDate(new Date());
        abcd.setWritepermission(true);
        hibernateDAO.saveOrUpdate(abcd);
        for (int i = 0; i < authorityList.size(); ++i) {
            UserBase_Authority userBase_authority = new UserBase_Authority();
            userBase_authority.setUserBase(abcd);
            userBase_authority.setAuthority(authorityList.get(i));
            hibernateDAO.saveOrUpdate(userBase_authority);
        }
    }

    //@Test
    @Transactional(rollbackFor = Exception.class, value = "transactionManager")
    public void initAuthority() {
        Authority authority_admin = new Authority();
        authority_admin.setAuthorityString("ROLE_ADMIN");
        authority_admin.setAuthorityDescription("管理员用户");
        authority_admin.setAuthorityExpireDate(new Date());
        authority_admin.setAuthorityStatus(0);
        hibernateDAO.saveOrUpdate(authority_admin);

        Authority authority_common = new Authority();
        authority_common.setAuthorityString("ROLE_COMMON");
        authority_common.setAuthorityDescription("普通用户");
        authority_common.setAuthorityExpireDate(new Date());
        authority_common.setAuthorityStatus(0);
        hibernateDAO.saveOrUpdate(authority_common);
    }

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

    @Test
    @Transactional
    public void testHibernateDAOInsertUserBase() {

        RandomModule randomModule = new RandomModuleImpl();
        int userNumber = 100;

        List<Authority> authorityList = hibernateDAO.listByAll(Authority.class,
                true);

        for (int i = 0; i < userNumber; ++i) {
            UserBase user = new UserBase();
            String username = randomModule.getRStr(RandomModule.myint_az, 4)
                    + randomModule.getRStr(RandomModule.myint_09, 3);
            user.setUserName(username);
            user.setPassWord(username);
            user.setWritepermission(randomModule.getRandomBoolean());
            String result = null;
            result = hibernateDAO.saveOrUpdate(user);
            logger.debug(result);

            UserBase_Authority userBase_authority = new UserBase_Authority();
            userBase_authority.setUserBase(user);
            userBase_authority.setAuthority(authorityList.get(randomModule
                    .getRandomInt(authorityList.size())));
            hibernateDAO.saveOrUpdate(userBase_authority);
        }
    }

}
