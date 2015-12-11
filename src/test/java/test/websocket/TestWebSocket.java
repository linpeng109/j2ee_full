package test.websocket;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import test.TestBase;

import javax.annotation.Resource;

/**
 * Created by jupiter on 15-12-7.
 */
public class TestWebSocket extends TestBase {

    public Logger logger = Logger.getLogger(TestWebSocket.class);

    @Resource
    public DirectChannel clientWebSocketOutboundChannel;

//    @Test
    public void testWebSocketClient() {
        Message message = MessageBuilder.withPayload("Test websocket").build();
        clientWebSocketOutboundChannel.send(message);
    }

    public void testWebSocketServer() {

    }
}
