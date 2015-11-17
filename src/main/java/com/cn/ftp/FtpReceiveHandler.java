package com.cn.ftp;

import org.apache.log4j.Logger;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jupiter on 15-9-17.
 */
public class FtpReceiveHandler {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(FtpReceiveHandler.class);

    /**
     * ftp处理函数
     *
     * @param message 接收到的消息
     * @return 返回消息
     * @throws IOException 抛出io错误
     */
    public Message handler(Message message) throws IOException {
        MessageHeaders headers = message.getHeaders();
        for (Map.Entry entry : headers.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.debug(String.format("The %s is %s in message header", key, value));
        }

        File payload = (File) message.getPayload();
        BufferedReader bufferedReader;
        bufferedReader = new BufferedReader(new FileReader(payload));
        String line = bufferedReader.readLine();
        StringBuffer stringBuffer = new StringBuffer();
        while (line != null) {
            stringBuffer.append(line);
        }
        logger.debug(stringBuffer.toString());


        logger.debug(payload.getClass().getTypeName());

        Message result = MessageBuilder.fromMessage(message).build();

        return result;
    }
}
