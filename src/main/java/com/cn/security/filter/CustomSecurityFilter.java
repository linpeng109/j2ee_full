package com.cn.security.filter;

import com.cn.crypto.implement.CryptoBASE64Impl;
import com.cn.crypto.implement.CryptoDigestImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by jupiter on 15-11-29.
 */
public class CustomSecurityFilter implements Filter {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(CustomSecurityFilter.class);

    /**
     * 认证授权服务器地址
     */
    private String securityHost;

    /**
     * 认证授权服务器端口号
     */
    private String securityPort;

    /**
     * 认证授权服务器应用名
     */
    private String securityAppname;

    /**
     * MD5数字指纹模块
     */
    CryptoDigestImpl digest = new CryptoDigestImpl();

    /**
     * Base64加密模块
     */
    CryptoBASE64Impl base64 = new CryptoBASE64Impl();

    /**
     * 由ticket加密后生成token
     *
     * @param digest MD5加密模块实例
     * @param base64 Base64处理模块实例
     * @param ticket ticket参数
     * @return 加密处理后的token
     */
    private String encoder(CryptoDigestImpl digest, CryptoBASE64Impl base64, String ticket) throws Exception {

        digest.setAlgorithmName(CryptoDigestImpl.MD5);

        String result = null;

        result = base64.encode(digest.encode(ticket));


        return result;
    }

    /**
     * Filter初始化
     *
     * @param filterConfig 配置实例
     * @throws ServletException servlet错误
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.securityHost = filterConfig.getInitParameter("securityHost");
        this.securityPort = filterConfig.getInitParameter("securityPort");
        this.securityAppname = filterConfig.getInitParameter("securityAppname");

        logger.debug("http://" + securityHost + ":" + securityPort + "/" + securityAppname);

    }

    /**
     * Http访问处理
     *
     * @param request  访问请求
     * @param response 访问回应
     * @param chain    filter链
     * @throws IOException      io错误
     * @throws ServletException servlet错误
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        /**
         * 获取sessionId的方法
         */
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        /**
         * 获取客户端ip地址的方法
         */
        String remoteAddr = httpRequest.getRemoteAddr();
        logger.debug("remoteAddr=" + remoteAddr);

        /**
         * 获取WebApplication端地址
         */
        String serverName = httpRequest.getServerName();
        logger.debug("serverName=" + serverName);

        /**
         * 获取request访问目标地路径
         */
        String requestURI = httpRequest.getRequestURI();
        logger.debug("requestURI=" + requestURI);

        /**
         * 获取访问参数的方法
         */
        String ticket = (String) httpRequest.getParameter("ticket");
        logger.debug("ticket=" + ticket);

        /**
         * 加密获取token的方法
         */
        String src = ticket + remoteAddr;
        String token = null;
        try {
            token = this.encoder(digest, base64, src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.debug("token=" + token);

        /**
         * 跳转的方法
         */
       // HttpServletResponse httpResponse = (HttpServletResponse) response;
       // httpResponse.sendRedirect("http://www.sina.com.cn");

        /**
         * 继续filter链
         */
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
