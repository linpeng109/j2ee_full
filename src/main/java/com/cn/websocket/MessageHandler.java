package com.cn.websocket;


import org.apache.log4j.Logger;

import javax.websocket.CloseReason;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
     * openSession集合
     */
    private static HashMap<String, Session> SESSIONMAP = new HashMap<String, Session>();


    /**
     * 根据sessionid获取session
     *
     * @param sessionId sessionid
     * @return session
     */
    public Session getSessionById(String sessionId) {
        if (!SESSIONMAP.isEmpty()) {
            Session session = SESSIONMAP.get(sessionId);
            return session;

        } else {
            logger.error("SESSIONMAP IS EMPTY");
        }
        return null;
    }

    /**
     * 向指定id的session（客户端）发送消息
     *
     * @param sessionId   sessionid
     * @param messageText 消息文本
     * @throws IOException
     */
    public void sendMessageToClient(String sessionId, String messageText) throws IOException {
        Session session = getSessionById(sessionId);
        if (!session.equals(null)) {
            String result = String.format("Server Send message [%s] to Cliend (sessionid=[%s])", messageText, sessionId);
            logger.debug(result);
            RemoteEndpoint.Basic basic = session.getBasicRemote();
            basic.sendText(result);
        } else {
            logger.error("session is null");
        }
    }

    /**
     * 向所有已经打开的client发送消息
     *
     * @param messageText 消息文本
     */
    public void sendMessageToAll(String messageText) throws IOException {
        if (!SESSIONMAP.isEmpty()) {
            for (Map.Entry entry : SESSIONMAP.entrySet()) {
                Session session = (Session) entry.getValue();
                String result = String.format("Server Send message [%s] to Cliend (sessionid=[%s])", messageText, session.getId());
                session.getBasicRemote().sendText(result);
            }
        }
    }

    /**
     * 当websocket打开时执行
     *
     * @param session 会话
     * @throws IOException 抛出io异常
     */
    public void onOpen(Session session) throws IOException {
        String result = String.format("Server websocket is opened .(sessionid=[%s],session.getRequestURI=[%s])", session.getId(), session.getRequestURI());
        session.getBasicRemote().sendText(result);
        String sessionid = session.getId();
        SESSIONMAP.put(sessionid, session);
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
        String sessionid = session.getId();
        String result = String.format("Server has received the message. (sessionid=[%s],message= [%s],PathParam=[%s])", sessionid, message, pathParam);
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
        SESSIONMAP.remove(session.getId());
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
