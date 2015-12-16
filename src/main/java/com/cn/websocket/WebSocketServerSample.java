package com.cn.websocket;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

/**
 * Created by jupiter on 15-12-14.
 */
@ServerEndpoint(value = "/time/{pathparam}")
public class WebSocketServerSample {
    private Logger logger = Logger.getLogger(WebSocketServerSample.class);

    @OnOpen
    public void onOpen(Session session) throws IOException {
        String result = String.format("The websocket is opened.session.getId=[%s];session.getRequestURI=[%s]", session.getId(), session.getRequestURI());
        session.getBasicRemote().sendText(result);
        logger.debug(result);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("pathparam") String pathParam) throws IOException {
        String result = String.format("Websocket Server has received the message. message= [%s] , PathParam=[%s]", message, pathParam);
        session.getBasicRemote().sendText(result);
        logger.debug(result);
    }

    @OnClose
    public void onClose(CloseReason closeReason) {
        String result = String.format("The websocket is closed. Close reason is [%s]", closeReason.getReasonPhrase());
        logger.debug(result);
    }

    @OnError
    public void onError(Throwable throwable) {
        logger.debug(throwable.getMessage());
    }
}
