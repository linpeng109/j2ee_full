package com.cn.xmpp.openfire;

import org.xml.sax.SAXException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.io.IOException;

@WebService
public interface UserService {

    /**
     * 添加好友
     *
     * @return 返回操作状态代码
     * @throws IOException  IO错误
     * @throws SAXException SAX错误
     */
    @WebMethod
    public abstract String addRoster() throws IOException, SAXException;

    /**
     * 设置用户可用
     *
     * @param username 用户名
     * @return 返回操作状态代码
     * @throws IOException  IO错误
     * @throws SAXException SAX错误
     */
    @WebMethod
    public abstract String enable(String username) throws IOException,
            SAXException;

    @WebMethod
    public abstract String disable(String username) throws IOException,
            SAXException;

    /**
     * 增加用户
     *
     * @return 返回操作状态代码
     * @throws SAXException SAX错误
     * @throws IOException  IO错误
     */
    @WebMethod
    public abstract String addUser() throws IOException, SAXException;

    /**
     * 删除好友
     *
     * @return 结果字符串
     * @throws IOException  io异常
     * @throws SAXException 解析错误
     */
    @WebMethod
    public abstract String deleteRoster() throws IOException, SAXException;

    /**
     * 删除用户
     *
     * @return 返回操作状态代码
     * @throws SAXException 解析错误
     * @throws IOException  io访问异常
     */
    @WebMethod
    public abstract String deleteUser() throws IOException, SAXException;

    @WebMethod
    public abstract String updateRoster() throws IOException, SAXException;

}