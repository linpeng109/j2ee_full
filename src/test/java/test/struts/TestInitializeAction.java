package test.struts;

import com.cn.struts2.InitializeAction;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import test.TestBase;

import javax.annotation.Resource;

/**
 * Created by linpeng109 on 15-8-24.
 */
public class TestInitializeAction extends TestBase {
    /**
     * 日志
     */
    final Logger logger = Logger.getLogger(TestInitializeAction.class);

    /**
     * 初始化模块
     */
    @Resource
    private InitializeAction initializeAction;

    /**
     * 测试初始化abcd用户
     */
    @Ignore
    private void testInitializeABCD() {
//        logger.debug(initializeAction.initializeABCD());
    }

    /**
     * 测试初始化权限用户
     */
    private void testInitializeAuthority() {
        logger.debug(initializeAction.initializeAuthority());
    }

    /**
     * 测试初始化userbase用户
     */
    private void testInitializeUserBase(int userNumber) {
        logger.debug(initializeAction.initializeUserBase(userNumber));

    }

    @Test
    public void init() {
        testInitializeAuthority();
        testInitializeABCD();
        testInitializeUserBase(100);
    }

}
