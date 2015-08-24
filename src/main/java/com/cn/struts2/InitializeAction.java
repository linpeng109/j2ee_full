package com.cn.struts2;

import com.cn.common.RandomModule;
import com.cn.dao.entity.Authority;
import com.cn.dao.entity.UserBase;
import com.cn.dao.entity.UserBase_Authority;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by linpeng109 on 15-8-24.
 */
public class InitializeAction extends BaseAction {
    /**
     * 日志
     */
    final Logger logger = Logger.getLogger(InitializeAction.class);

    /**
     * 初始化权限列表
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class, value = "transactionManager")
    public String initializeAuthority() {
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
        return super.SUCCESS;
    }

    /**
     * 初始化abcd用户
     *
     * @return
     */
    @Transactional(rollbackFor = Exception.class, value = "transactionManager")
    public String initializeABCD() {
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
        return super.SUCCESS;
    }

    /**
     * 初始化userbase用户列表
     */
    @Transactional(rollbackFor = Exception.class, value = "transactionManager")
    public String initializeUserBase(int userNumber) {

        List<Authority> authorityList = hibernateDAO.listByAll(Authority.class,
                true);

        for (int i = 0; i < userNumber; ++i) {
            UserBase user = new UserBase();
            String username = randomModule.getRStr(RandomModule.mystring_china, 2)
                    + randomModule.getRStr(RandomModule.myint_az, 2)
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
        return super.SUCCESS;
    }
}
