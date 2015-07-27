package com.cn.encoder;

import org.apache.log4j.Logger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteScriptAction {
    private Logger logger = Logger.getLogger(ExecuteScriptAction.class);

    public Message<String> execute(Message<String> message) throws Exception {

        /**
         * 获取参数
         */
        MessageHeaders headers = message.getHeaders();
        String scriptFileFullPath = (String) headers.get("scriptFileFullPath");

        /**
         * 执行批处理文件
         */
        Runtime runtime = Runtime.getRuntime();
        Process encoderProcess = runtime.exec(scriptFileFullPath);
        final BufferedReader br = new BufferedReader(new InputStreamReader(
                encoderProcess.getErrorStream()));
        /**
         * 启动单独的线程来清空encoderProcess.getErrorStream的缓冲区
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (br.readLine() != null) {
                    }
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }).start();
        /**
         * 从encoderProcess.getInputStream()获得编码信息
         */
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(encoderProcess.getInputStream()), 1024);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            /**
             * 显示编码信息
             */
            logger.debug(line);
        }
        br.close();
        try {
            logger.debug("encoderProcess return " + encoderProcess.waitFor());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        encoderProcess.destroy();

        /**
         * 生成新消息
         */
        String payload = message.getPayload();
        Message<String> result = MessageBuilder.withPayload(payload)
                .copyHeaders(headers).build();

        return result;
    }

}
