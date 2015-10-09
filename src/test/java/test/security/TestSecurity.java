package test.security;

import com.cn.security.HelloWorldMessageService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import test.TestBase;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by jupiter on 15-10-9.
 */
public class TestSecurity extends TestBase {
    private static Logger logger = Logger.getLogger(TestSecurity.class);

    @Resource
    private HelloWorldMessageService helloWorldMessageService;

    @Test
    @WithMockUser(username="abcd",authorities={"ROLE_ADMIN"})
    //@WithUserDetails("abcd")
    public void testGetAuthenticationResult() {
        Map<String, Object> result = helloWorldMessageService.getAuthenticationResult();
        for (Map.Entry entry : result.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.debug(String.format("The %s is %s in result", key, value.toString()));
        }
    }
}
