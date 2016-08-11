package test.websocket;

import com.cn.common.RandomModule;
import com.cn.common.implement.RandomModuleImpl;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by jupiter on 15-12-7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestWebSocket {

    public Logger logger = Logger.getLogger(TestWebSocket.class);

    //@Resource
    public MessageChannel clientWebSocketOutboundChannel;

    @Test
    @Ignore
    public void testWebSocketClient() {
        RandomModule random = new RandomModuleImpl();
        for (int i = 0; i < 100; ++i) {
            String payload = random.getRStr(RandomModule.mystring_china, 3);
            Message message = MessageBuilder.withPayload("Test websocket " + payload).build();
            clientWebSocketOutboundChannel.send(message);
        }
    }
}
