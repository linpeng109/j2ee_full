package com.cn.security;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Iterator;

/**
 * Created by jupiter on 15-10-12.
 * 获取用户的权限认证
 */
public class AuthenticationUtil {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(AuthenticationUtil.class);

    /**
     * 依赖注入获取
     */
    private AuthenticationManager authenticationManager;

    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 用户权限认证
     *
     * @param authentication 权限认证
     * @return 用户Authentication
     */
    private Authentication authenticate(Authentication authentication) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(authentication);
            SecurityContextHolder.getContext().setAuthentication(result);
        } catch (Exception ex) {
            logger.error(ex);
            result = null;
        }
        return result;
    }

    /**
     * 获取权限字符串组
     *
     * @param authentication 指定认证
     * @return 权限字符串组
     */
    public String[] getAuthorities(Authentication authentication) {
        StringBuffer buffer = new StringBuffer();
        Iterator iterator1 = authentication.getAuthorities().iterator();
        while (iterator1.hasNext()) {
            SimpleGrantedAuthority simpleGrantedAuthority = (SimpleGrantedAuthority) iterator1.next();
            String authority = simpleGrantedAuthority.getAuthority();
            buffer.append(authority);
        }
        return buffer.toString().split(";");
    }

    /**
     * 判断权限字符串组中是否包含指定权限
     *
     * @param authentication  权限实例
     * @param authorityString 指定权限字符串
     * @return 是否包含指定权限
     */
    public boolean isContains(Authentication authentication, String authorityString) {


        return true;
    }


    /**
     * 显示Authentication内容
     *
     * @param authentication 用户Authentication
     */
    public void displayAuthentication(Authentication authentication) {
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
        logger.debug(String.format("principal.isAccountNotLocked=%s", isAccountNotLocked));
        logger.debug(String.format("principal.isAccountNonExpired=%s", isAccountNonExpired));
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
        boolean credentialsIsNull = (authentication.getCredentials() == null);
        logger.debug(String.format("authentication.getCredentials() == null? %s", credentialsIsNull));

        /**
         * about details
         */
        boolean detailsIsNull = (authentication.getDetails() == null);
        logger.debug(String.format("authentication.getDetails() == null? %s", detailsIsNull));


    }

    /**
     * 通过用户名、口令获取权限认证
     *
     * @param username 用户名
     * @param password 口令
     * @return 权限认证
     */
    public Authentication authenticateByUsernamePassword(String username, String password) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return this.authenticate(authenticationToken);
    }

    /**
     * 通过当前用户获取权限认证
     *
     * @return 权限认证
     */
    public Authentication authenticateByCurrentUser() {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        return this.authenticate(authenticationToken);

    }
}
