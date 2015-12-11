package com.cn.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by jupiter on 15-11-29.
 */
public class CustomLogoutSuccessHandler implements LogoutHandler {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(CustomLogoutSuccessHandler.class);

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String sessionId = request.getSession().getId();
        logger.debug("删除cache中的token");
        logger.debug(String.format("The CustomLoggerHandler is executed success!"));
    }

}
