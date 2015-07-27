package com.cn.security;

import org.springframework.flex.security3.AuthenticationResultUtils;

import java.util.Map;

public class Security3Helper {
    public Map<String, Object> getAuthentication() {
        return AuthenticationResultUtils.getAuthenticationResult();
    }
}
