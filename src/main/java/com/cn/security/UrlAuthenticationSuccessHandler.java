package com.cn.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当用户认证授权通过时执行该类的onAuthenticationSuccess函数
 */
public class UrlAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static Logger logger = Logger.getLogger(UrlAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.debug("UrlAuthenticationSuccessHandler is executed !");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
