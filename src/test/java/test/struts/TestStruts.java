package test.struts;

import com.cn.struts2.HelloWorldAction;
import org.apache.log4j.Logger;
import org.junit.Test;
import test.TestBase;

import javax.annotation.Resource;

/**
 * Created by linpeng109 on 15-7-29.
 */
public class TestStruts extends TestBase {

    private static Logger logger = Logger.getLogger(TestStruts.class);

    @Resource
    private HelloWorldAction helloWorldAction;

    @Test
    public void hello() {
        logger.debug("TestDao is starting......");
        helloWorldAction.hello();
    }

}
