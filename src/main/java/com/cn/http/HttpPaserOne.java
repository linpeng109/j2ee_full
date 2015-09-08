package com.cn.http;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Map;

/**
 * Created by linpeng109 on 15-8-26.
 */
public class HttpPaserOne {
    /**
     *
     */
    final Logger logger = Logger.getLogger(HttpPaserOne.class);

    /**
     * @return
     */
    public Message<String> handler(Message<LinkedMultiValueMap> msg) {
        Map<String, String> headers = msg.getPayload();
        for (Map.Entry entry : headers.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.debug(String.format("The %s is %s in message header", key, value));
        }

        LinkedMultiValueMap<Object, Object> payload = msg.getPayload();
        for (Map.Entry entry : payload.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.debug(String.format("The %s is %s in message payload", key, value));
        }

        logger.debug(payload);
        Message<String> result = MessageBuilder
                .withPayload(String.format("The bean %s is success.\n", this.getClass().getName()))
                .copyHeaders(msg.getHeaders())
                .build();
        return result;
    }
}
