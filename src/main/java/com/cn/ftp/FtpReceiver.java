package com.cn.ftp;

import org.apache.log4j.Logger;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

/**
 * Created by jupiter on 15-9-17.
 */
public class FtpReceiver {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(FtpReceiver.class);

    /**
     * ftp处理函数
     *
     * @param message
     * @return
     */
    public Message handler(Message message) {
        MessageHeaders headers = message.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.debug(String.format("The %s is %s in message header", key, value));
        }


        Object payload = message.getPayload();
        logger.debug(payload.getClass().getTypeName());

        Message result = MessageBuilder.fromMessage(message).build();

        return result;
    }
}
