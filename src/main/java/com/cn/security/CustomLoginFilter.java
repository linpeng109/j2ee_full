package com.cn.security;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by jupiter on 15-11-30.
 */
class CustomLoginFilter extends UsernamePasswordAuthenticationFilter {
    private Logger logger = Logger.getLogger(CustomLoginFilter.class);

    public CustomLoginFilter() {
        super();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String ticket = request.getSession().getId();
        logger.debug("ticket=" + ticket);
        return super.attemptAuthentication(request, response);
    }
}
