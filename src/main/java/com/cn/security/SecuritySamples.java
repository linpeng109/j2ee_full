package com.cn.security;


import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jupiter on 15-10-9.
 */
public class SecuritySamples {
    private static Logger logger = Logger.getLogger(SecuritySamples.class);

    public void securityMethod() {
        String result = "The securing method securityMethod executed successfully";
        logger.debug(result);
    }


}