package com.cn.ip;

import org.apache.log4j.Logger;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.util.Map;

public class TCPMessageReceiver {
    private Logger logger = Logger.getLogger(TCPMessageReceiver.class);

    public TCPMessageReceiver() {
    }

    public Message<String> receiver(Message<byte[]> message) {

        MessageHeaders headers = message.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.debug(String.format("The %s is %s in message header", key, value));
        }

        byte[] payload = message.getPayload();
        StringBuffer buffer = new StringBuffer();
        for (byte item : payload) {
            buffer.append((char) item);
        }

        logger.debug(String.format("The payload is %s", buffer.toString()));

        Message result = MessageBuilder.withPayload(buffer.toString()).copyHeaders(headers).build();
        return result;
    }
}
