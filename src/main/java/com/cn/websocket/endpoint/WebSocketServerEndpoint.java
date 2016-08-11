package com.cn.websocket.endpoint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by jupiter on 15-12-14.
 */
@ServerEndpoint(value = "/sample/{pathparam}", configurator = SpringConfigurator.class)
public class WebSocketServerEndpoint {
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(WebSocketServerEndpoint.class);

    /**
     * 日志通道
     */
    private final com.cn.websocket.MessageHandler messageHandler;


    /**
     * 构造函数
     *
     * @param messageHandler 消息处理
     */
    @Autowired
    public WebSocketServerEndpoint(com.cn.websocket.MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.messageHandler.onOpen(session);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("pathparam") String pathParam) throws IOException {
        this.messageHandler.onMessage(message, session, pathParam);
    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        this.messageHandler.onClose(session, closeReason);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        this.messageHandler.onError(session, throwable);
    }

}
