package com.cn.security;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by jupiter on 15-10-12.
 * 获取用户的权限认证
 */
public class SecurityAuthentication {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(SecurityAuthentication.class);

    /**
     * 依赖注入获取
     */
    private AuthenticationManager authenticationManager;

    /**
     * 用户权限认证
     *
     * @param authentication 权限认证
     * @return
     */
    private Authentication authenticate(Authentication authentication) {
        Authentication result = null;
        try {
            result = authenticationManager.authenticate(authentication);

        } catch (Exception ex) {
            logger.error(ex);
            result = null;
        }
        return result;
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
