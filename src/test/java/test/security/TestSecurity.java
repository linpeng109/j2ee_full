package test.security;

import com.cn.security.SecuritySamples;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import test.TestBase;

import javax.annotation.Resource;

/**
 * Created by jupiter on 15-10-9.
 */
public class TestSecurity extends TestBase {
    private static Logger logger = Logger.getLogger(TestSecurity.class);

    @Resource
    private SecuritySamples securitySamples;

    public void testGetAuthenticationResult() {
    }

    @Test
    //@WithMockUser(username = "abcde", authorities = {"ROLE_ADMIN"})
    @WithUserDetails("abcd")
    public void testSecurityBy_user_1() {
        securitySamples.securityMethod();
    }

    @Test
    @WithUserDetails("史杨ak261")
    public void testSecurityBy_user_2() {
        securitySamples.securityMethod();
    }

}
