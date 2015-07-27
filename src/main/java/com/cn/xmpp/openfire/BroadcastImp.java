package com.cn.xmpp.openfire;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * Openfire消息服务器消息广播
 *
 * @author admin
 */
public class BroadcastImp implements Broadcast {
    private Logger logger = Logger.getLogger(BroadcastImp.class);
    private String serviceName = "broadcast";
    private String serverName;
    private MessageChannel xmppOutputChannel;

    /* (non-Javadoc)
     * @see com.cn.xmpp.openfire.Broadcast#broadcastHandler(java.lang.String, java.lang.String)
     */
    @Override
    public void broadcastHandler(String groupName, String payload) {
        String to = groupName + "@" + serviceName + "." + serverName;
        logger.debug(to);
        Message<String> message = MessageBuilder.withPayload(payload)
                .setHeader("xmpp_to", to).build();
        xmppOutputChannel.send(message);
    }

    public String getServerName() {
        return serverName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public MessageChannel getXmppOutputChannel() {
        return xmppOutputChannel;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setXmppOutputChannel(MessageChannel xmppOutputChannel) {
        this.xmppOutputChannel = xmppOutputChannel;
    }

}
