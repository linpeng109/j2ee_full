package com.cn.xmpp.openfire;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface Broadcast {

    /**
     * 发送XMPP消息
     *
     * @param groupName 用户组名称
     * @param payload   消息体（字符串型）
     */
    @WebMethod
    public abstract void broadcastHandler(String groupName, String payload);

}