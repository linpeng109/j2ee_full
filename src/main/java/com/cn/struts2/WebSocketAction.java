package com.cn.struts2;

import com.cn.websocket.MessageHandler;
import org.apache.log4j.Logger;

import javax.websocket.Session;
import java.io.IOException;

/**
 * Created by jupiter on 16-1-4.
 */
public class WebSocketAction extends BaseAction {

    private static Logger logger = Logger.getLogger(WebSocketAction.class);
    public String sessionId;
    public String messageText;

    /**
     * 依赖注入获取Websocket的MessageHandler实例
     */
    public MessageHandler messageHandler;

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public void setMessageHandler(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    /**
     * 向指定id的websocket客户端发送消息文本
     *
     * @return
     */
    public String sendMessageToClient() throws IOException {
        Session session = messageHandler.getSessionById(this.getSessionId());
        if (session != null) {
            messageHandler.sendMessageToClient(sessionId,messageText);
            return super.SUCCESS;
        } else {
            logger.error("session is null");
        }
        return super.ERROR;
    }
    public String sendMessageToAll() throws IOException {

        messageHandler.sendMessageToAll(messageText);
        return super.SUCCESS;
    }
}
