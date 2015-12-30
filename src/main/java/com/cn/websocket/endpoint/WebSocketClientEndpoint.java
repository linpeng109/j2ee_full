package com.cn.websocket.endpoint;

import org.apache.log4j.Logger;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import java.io.IOException;

/**
 * Created by jupiter on 15-12-29.
 */
@ClientEndpoint
public class WebSocketClientEndpoint {

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(this.getClass().getName());


    @OnOpen
    public void onOpen(Session session) throws IOException {
        String result = String.format("Connected ...... sessionId=%s", session.getId());
        logger.debug(result);
        session.getBasicRemote().sendText(result);

    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("pathparam") String pathParam) throws IOException {
        String result = String.format("Receive OK!  Message= [%s] , PathParam=[%s]", message, pathParam);
        session.getBasicRemote().sendText(result);
        logger.debug(result);

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        String result = String.format("Closed ...... %s", closeReason.getReasonPhrase());
        logger.debug(result);
    }

    @OnError
    public void onError(Throwable throwable) {
        String result = String.format("Error ...... %S", throwable.getMessage());
        logger.debug(result);
    }

}
