package com.cn.http;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Created by linpeng109 on 15-8-26.
 */
public class HttpPaserTwo {
    /**
     *
     */
    final Logger logger = Logger.getLogger(HttpPaserTwo.class);

    /**
     * 消息处理
     *
     * @param msg 输入消息
     * @return 输出消息
     */
    public Message<String> handler(Message<String> msg) {
        String payload = msg.getPayload() + "2";
        logger.debug(payload);
        Message<String> result = MessageBuilder
                .withPayload(String.format("The bean %s is success.\n", this.getClass().getName()))
                .copyHeaders(msg.getHeaders())
                .build();
        return result;
    }
}
