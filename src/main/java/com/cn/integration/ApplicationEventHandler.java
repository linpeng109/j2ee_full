package com.cn.integration;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

public class ApplicationEventHandler {
    public Logger logger = Logger.getLogger(ApplicationEventHandler.class);

    public String type;
    private MessageChannel channel;

    public MessageChannel getChannel() {
        return channel;
    }

    public String getType() {
        return type;
    }

    public Message<String> receive(Message<String> message) {

        Message<String> result = MessageBuilder.fromMessage(message).build();
        logger.debug(message);
        return result;
    }

    public String send(String input) {
        Message<String> message = MessageBuilder.withPayload(input).build();
        this.getChannel().send(message);
        return "success";
    }

    public void setChannel(MessageChannel channel) {
        this.channel = channel;
    }

    public void setType(String type) {
        this.type = type;
    }
}
