package com.cn.ip;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;

public class UDPMessageReceiver {
    private Logger logger = Logger.getLogger(UDPMessageReceiver.class);

    public UDPMessageReceiver() {
    }

    public void receiver(Message<?> message) {
        byte[] bytes = (byte[]) message.getPayload();
        String result = new String(bytes);
        logger.debug("收到消息：" + result);
    }
}
