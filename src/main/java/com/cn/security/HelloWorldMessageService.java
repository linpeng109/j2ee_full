package com.cn.security;


import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by jupiter on 15-10-9.
 */
public class HelloWorldMessageService {
    private static Logger logger = Logger.getLogger(HelloWorldMessageService.class);

    public Map<String, Object> getAuthenticationResult() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        } else {
            HashMap authenticationResult = new HashMap();
            authenticationResult.put("name", authentication.getName());
            String[] authorities = new String[authentication.getAuthorities().size()];
            int i = 0;

            GrantedAuthority granted;
            for (Iterator i$ = authentication.getAuthorities().iterator(); i$.hasNext(); authorities[i++] = granted.getAuthority()) {
                granted = (GrantedAuthority) i$.next();
            }

            authenticationResult.put("authorities", authorities);
            return authenticationResult;
        }
    }
}

