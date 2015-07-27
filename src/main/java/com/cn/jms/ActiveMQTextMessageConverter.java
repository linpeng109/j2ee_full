package com.cn.jms;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.Logger;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.io.IOException;

/**
 * 将ActiveMQTextMessage类型的消息和Spring—Integration中的消息转换
 *
 * @author Administrator
 */
public class ActiveMQTextMessageConverter implements MessageConverter {
    public Logger logger = Logger.getLogger(ActiveMQTextMessageConverter.class);

    @Override
    public Object fromMessage(Message msg) throws JMSException,
            MessageConversionException {
        ActiveMQTextMessage activeMsg = (ActiveMQTextMessage) msg;
        String userID = null;
        String groupID = null;
        String messageID = null;
        String correlationId = null;
        try {
            userID = (String) activeMsg.getProperty("userID");
            groupID = (String) activeMsg.getProperty("groupID");
            messageID = (String) activeMsg.getProperty("messageID");
            correlationId = (String) activeMsg.getProperty("correlationId");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String payload = activeMsg.getText();
        org.springframework.messaging.Message<String> result = MessageBuilder
                .withPayload(payload).setHeader("correlationId", correlationId)
                .setHeader("messageID", messageID).setHeader("userID", userID)
                .setHeader("groupID", groupID)
                .setHeader("correlationId", correlationId).build();
        return result;
    }

    @Override
    public Message toMessage(Object msg, Session session) throws JMSException,
            MessageConversionException {
        @SuppressWarnings("unchecked")
        org.springframework.messaging.Message<String> siMessage = ((org.springframework.messaging.Message<String>) msg);
        String payload = siMessage.getPayload();
        MessageHeaders messageHeaders = siMessage.getHeaders();
        String messageID = (String) messageHeaders.get("messageID");
        String userID = (String) messageHeaders.get("userID");
        String groupID = (String) messageHeaders.get("groupID");
        String correlationId = (String) messageHeaders.get("correlationId");
        ActiveMQTextMessage activeMsg = new ActiveMQTextMessage();
        try {
            activeMsg.setProperty("messageID", messageID);
            activeMsg.setProperty("userID", userID);
            activeMsg.setProperty("groupID", groupID);
            activeMsg.setProperty("correlationId", correlationId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        activeMsg.setText(payload);
        return activeMsg;
    }

}
