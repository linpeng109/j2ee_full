package com.cn.encoder;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;

/**
 * @author SUN
 */
public class CleanAction {
    public Message<String> execute(Message<String> message) {

        /**
         * 获取参数
         */
        MessageHeaders headers = message.getHeaders();
        String outputPathAndOutputFileMainName = (String) headers
                .get("outputPathAndOutputFileMainName");
        int captureTimes = (int) headers.get("captureTimes");

        StringBuffer buffer = new StringBuffer();
        buffer.append(outputPathAndOutputFileMainName + "_tmp" + ".avi")
                .append(";");
        buffer.append(outputPathAndOutputFileMainName + "_index" + ".avi")
                .append(";");
        for (int i = 0; i < captureTimes; ++i) {
            buffer.append(outputPathAndOutputFileMainName + "_src_" + i
                    + ".jpg");
            buffer.append(";");
        }

        /**
         * 删除临时文件
         */
        String[] files = buffer.toString().split(";");
        for (int i = 0; i < files.length; ++i) {
            File file = new File(files[i]);
            file.delete();
        }

        /**
         * 生成新消息
         */
        String payload = message.getPayload();
        Message<String> result = MessageBuilder.withPayload(payload)
                .copyHeaders(headers).build();
        return result;

    }

}
