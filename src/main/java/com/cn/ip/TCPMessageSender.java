package com.cn.ip;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

public class TCPMessageSender {

    @Resource
    public MessageChannel tcpRequestOutputChannel;

    public boolean sender() {
        byte[] payload = {'a', 'b', 'c'};
        Message<byte[]> message = MessageBuilder.withPayload(payload).build();
        tcpRequestOutputChannel.send(message);
        return true;
    }
}
