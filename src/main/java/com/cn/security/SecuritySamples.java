package com.cn.security;


import org.apache.log4j.Logger;

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