package test.http;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.messaging.MessageChannel;
import test.TestBase;

import javax.annotation.Resource;

/**
 * Created by linpeng109 on 15-8-28.
 */
public class TestHttp extends TestBase {
    final Logger logger = Logger.getLogger(TestHttp.class);
    @Resource
    private MessageChannel httpPaserTwoInputChannel;

    @Test
    public void testRequestChannel() {
//        Message<String> message = MessageBuilder
//                .withPayload("Test httpPaser ")
//                .setHeader("channel", "2")
//                .build();
//        httpPaserTwoInputChannel.send(message);
    }

}
