package test.webservice;

import com.cn.webservice.jaxws.HelloWorldWebService;
import org.apache.log4j.Logger;
import org.junit.Test;
import test.TestBase;

import javax.annotation.Resource;

/**
 * Created by jupiter on 15-9-30.
 */
public class TestHelloWorldWebServicee extends TestBase {

    private static Logger logger = Logger.getLogger(TestHelloWorldWebServicee.class);

    @Resource
    private HelloWorldWebService client;

    @Test
    public void testHelloWorld() {
        String result = client.helloWorldByString("linpeng");
        logger.debug(result);
    }
}
