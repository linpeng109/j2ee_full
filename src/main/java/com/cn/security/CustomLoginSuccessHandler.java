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
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(CustomLoginSuccessHandler.class);

    /**
     * 当认证成功时执行
     */

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String sessionid = request.getSession().getId();
        logger.debug("获取sessionid=ticket");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
