package com.cn.struts2;

import com.cn.common.DateModule;
import com.cn.common.RandomModule;
import com.cn.crypto.CryptoModule;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.StrutsStatics;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 所有Action类基类，完成各公用系统模块的注入 2010-03-30修改获得用户登录权限和认证信息的函数
 *
 * @author Hartwell
 * @version 2010.03.30
 */
@SuppressWarnings("serial")
public abstract class BaseAction extends ActionSupport implements
        ApplicationContextAware {

    /**
     * 获取Spring容器
     */
    private ApplicationContext applicationContext;

    /**
     * 获取Struts2容器
     */
    private ActionContext context;

    /**
     * 获取http请求的request
     */
    private HttpServletRequest request;

    /**
     * 获取http请求的response
     */
    private HttpServletResponse response;

    /**
     * 依赖注入获得时间操作模块实例
     */
    public DateModule dateModule;

    /**
     * 依赖注入获得随机数操作模块实例
     */
    public RandomModule randomModule;

    /**
     * 依赖注入获得加密解密模块实例
     */
    public CryptoModule cryptoModule;

    /**
     * 构造函数
     */
    public BaseAction() {
    }

    /**
     * @return the applicationContext
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取Spring Security的认证信息
     *
     * @return 权限对象
     */
    public Authentication getAuthentication() {
        Authentication result = SecurityContextHolder.getContext()
                .getAuthentication();
        return result;
    }

    /**
     * 获取获取Spring_Security的权限字符
     *
     * @return 权限列表
     */
    public List<String> getAuthoritiesString() {
        List<String> authorityList = new ArrayList<String>();
        Collection<?> grantedAuthorities = SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        for (Object authority : grantedAuthorities) {

            authorityList.add(((GrantedAuthority) authority).getAuthority());
        }
        return authorityList;
    }

    public ActionContext getContext() {
        context = ActionContext.getContext();
        return context;
    }

    public CryptoModule getCryptoModule() {
        return cryptoModule;
    }

    public DateModule getDateModule() {
        return dateModule;
    }

    public RandomModule getRandomModule() {
        return randomModule;
    }

    public HttpServletRequest getRequest() {
        request = (HttpServletRequest) getContext().get(
                StrutsStatics.HTTP_REQUEST);
        return request;
    }

    public HttpServletResponse getResponse() {
        response = (HttpServletResponse) getContext().get(
                StrutsStatics.HTTP_RESPONSE);
        return response;
    }

    /**
     * @param applicationContext the applicationContext to set
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void setContext(ActionContext context) {
        this.context = context;
    }

    public void setCryptoModule(CryptoModule cryptoModule) {
        this.cryptoModule = cryptoModule;
    }

    public void setDateModule(DateModule dateModule) {
        this.dateModule = dateModule;
    }

    public void setRandomModule(RandomModule randomModule) {
        this.randomModule = randomModule;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
