package com.cn.http;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by linpeng109 on 15-8-28.
 */
public class HttpPaserThree {
    /**
     *
     */
    final Logger logger = Logger.getLogger(HttpPaserThree.class);

    /**
     * 消息处理
     *
     * @param msg 输入消息
     * @return 输出消息
     */
    public Message<String> handler(Message<String> msg) {
        String payload = msg.getPayload() + "3";
        logger.debug(payload);
        Message<String> result = MessageBuilder
                .withPayload(String.format("The bean %s is success.\n", this.getClass().getName()))
                .copyHeaders(msg.getHeaders())
                .build();

        return result;
    }
}
