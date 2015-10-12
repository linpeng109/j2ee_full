package test.security;

import com.cn.security.SecuritySamples;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

    public void testGetAuthenticationResult() {
    }

    @Test
    public void testAuthenticationManager() {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken("abcde", "abcd");
        try {
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            /**
             * about principal
             */
            User user = (User) authentication.getPrincipal();
            String username = user.getUsername();
            String password = user.getPassword();
            boolean isEnable = user.isEnabled();
            StringBuffer authorities = new StringBuffer();
            Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
            while (iterator.hasNext()) {
                GrantedAuthority grantedAuthority = iterator.next();
                String authority = grantedAuthority.getAuthority();
                authorities.append(authority);
                authorities.append(";");
            }
            boolean isAccountNonExpired = user.isAccountNonExpired();
            boolean isAccountNotLocked = user.isAccountNonLocked();
            boolean isCreadentialsNonExpired = user.isCredentialsNonExpired();
            logger.debug(String.format("principal.username=%s", username));
            logger.debug(String.format("principal.password=%s", password));
            logger.debug(String.format("principal.isEnable=%s", isEnable));
            logger.debug(String.format("principal.authorities=%s", authorities.toString()));
            logger.debug(String.format("principal.isAccountNonExpired=%s", isAccountNonExpired));
            logger.debug(String.format("principal.isAccountNotLocked=%s", isAccountNotLocked));
            logger.debug(String.format("principal.isCreadentialsNonExpired=%s", isCreadentialsNonExpired));

            /**
             * about isAuthenticated
             */
            boolean isAuthenticated = authentication.isAuthenticated();
            logger.debug(String.format("isAuthenticated=%s", isAuthenticated));

            /**
             *about authorities
             */
            Iterator iterator1 = authentication.getAuthorities().iterator();
            while (iterator1.hasNext()) {
                SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) iterator1.next();

                logger.debug(simpleGrantedAuthority.getAuthority());
            }

            /**
             * about credentials
             */
            assertNull(authentication.getCredentials());
            logger.debug(authentication.getCredentials() == null);

            /**
             * about details
             */
            logger.debug(authentication.getDetails() == null);
        } catch (Exception ex) {
            logger.error(ex);
        }
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
