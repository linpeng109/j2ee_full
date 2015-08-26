package com.cn.http;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

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
    public Message<String> handler(Message<String> msg) {
        String payload = msg.getPayload();
        logger.debug(payload);
        Message<String> result = MessageBuilder
                .withPayload(payload + "_1")
                .copyHeaders(msg.getHeaders())
                .build();
        return result;
    }
}
