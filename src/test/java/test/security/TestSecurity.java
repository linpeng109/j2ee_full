package test.security;

import com.cn.security.AuthenticationUtil;
import com.cn.security.SecuritySamples;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import test.TestBase;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by jupiter on 15-10-9.
 */
public class TestSecurity extends TestBase {
    private static Logger logger = Logger.getLogger(TestSecurity.class);

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private SecuritySamples securitySamples;

    @Resource
    private AuthenticationUtil authenticationUtil;


    public void testGetAuthenticationResult() {
    }

    /**
     * 测试用户认证
     */
    @Test
    public void testAuthenticationManager() {
        logger.debug("testAuthenticationManager......");
        Authentication authentication = null;
        try {
            authentication = authenticationUtil.authenticateByUsernamePassword("abcd", "abcd");
        } catch (Exception ex) {
            logger.error(ex);
        }
    }

    @Test
    public void testDispalyAuthentication() {
        logger.debug("testDispalyAuthentication......");
        Authentication authentication = null;
        try {
            authentication = authenticationUtil.authenticateByUsernamePassword("abcd", "abcd");
        } catch (Exception ex) {
            logger.error(ex);
        }
        if (authentication != null) {
            authenticationUtil.displayAuthentication(authentication);
        }
    }

    @Test
    public void testGetAuthorities() {
        logger.debug("testGetAuthorities......");
        Authentication authentication = null;
        try {
            authentication = authenticationUtil.authenticateByUsernamePassword("abcd", "abcd");
        } catch (Exception ex) {
            logger.error(ex);
        }
        if (authentication != null) {
            String[] authorities = authenticationUtil.getAuthorities(authentication);

            for (int i = 0; i < authorities.length; ++i) {
                logger.debug("--" + authorities[i]);
            }
        }
    }

    @Test
    @WithMockUser(username = "abcde", authorities = {"ROLE_ADMIN"})
    public void testSecurityBy_mockUser() {
        logger.debug("testSecurityBy_mockUser......");
        securitySamples.securityMethod();
    }

    @Test
    @WithUserDetails("abcd")
    public void testSecurityBy_user_1() {
        logger.debug("testSecurityBy_user_1......");
        securitySamples.securityMethod();
        authenticationUtil.displayAuthentication(authenticationUtil.authenticateByCurrentUser());
    }

    @Test
    @WithUserDetails("abcd")
    public void testSecurityBy_user_2() {
        logger.debug("testSecurityBy_user_2......");
        securitySamples.securityMethod();
        authenticationUtil.displayAuthentication(authenticationUtil.authenticateByCurrentUser());
    }
}