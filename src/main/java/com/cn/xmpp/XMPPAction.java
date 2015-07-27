package com.cn.xmpp;

import com.cn.struts2.BaseAction;
import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class XMPPAction extends BaseAction {
    public MessageChannel xmppOutputChannel;
    public String chatTo;
    public String payload;
    public Logger logger = Logger.getLogger(XMPPAction.class);

    public String getChatTo() {
        return chatTo;
    }

    public MessageChannel getXmppOutputChannel() {
        return xmppOutputChannel;
    }

    public String sendXMPPMessage() {
        SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date = formate.format(new Date());
        payload = "XMPP测试：" + date;
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("to", chatTo).build();
        logger.debug("chatTo=" + chatTo);
        logger.debug("payload=" + payload);
        logger.debug("send=" + xmppOutputChannel.send(message));
        return Action.SUCCESS;
    }

    public void setChatTo(String chatTo) {
        this.chatTo = chatTo;
    }

    public void setXmppOutputChannel(MessageChannel xmppOutputChannel) {
        this.xmppOutputChannel = xmppOutputChannel;
    }
}
