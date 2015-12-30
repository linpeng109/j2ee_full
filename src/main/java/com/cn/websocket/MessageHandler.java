package com.cn.websocket;


import org.apache.log4j.Logger;

import javax.websocket.CloseReason;
import javax.websocket.Session;
import java.io.IOException;

/**
 * Websocket消息管理
 * Created by jupiter on 15-12-30.
 */
public class MessageHandler {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(MessageHandler.class);

    /**
     * 当websocket打开时执行
     *
     * @param session 会话
     * @throws IOException 抛出io异常
     */
    public void onOpen(Session session) throws IOException {
        String result = String.format("Welcome ! The Websocket is opened. session.getId=[%s];session.getRequestURI=[%s]", session.getId(), session.getRequestURI());
        session.getBasicRemote().sendText(result);
        logger.debug(result);
    }

    /**
     * 当服务器端收到消息时执行
     *
     * @param message   消息文本
     * @param session   会话
     * @param pathParam 参数
     * @throws IOException 抛出io异常
     */
    public void onMessage(String message, Session session, String pathParam) throws IOException {
        String result = String.format("Greeting received the message. message= [%s] , PathParam=[%s]", message, pathParam);
        session.getBasicRemote().sendText(result);
        logger.debug(result);
    }

    /**
     * 当websocket关闭时执行
     *
     * @param session     会话
     * @param closeReason 关闭原因
     */
    public void onClose(Session session, CloseReason closeReason) {
        logger.debug(closeReason);
    }

    /**
     * 当websocket错误时执行
     *
     * @param session   会话
     * @param throwable 抛出错误
     */
    public void onError(Session session, Throwable throwable) {
        logger.error(throwable);
    }
}
