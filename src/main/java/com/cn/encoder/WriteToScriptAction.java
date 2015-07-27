package com.cn.encoder;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.io.*;

public class WriteToScriptAction {

    private String outputPath;

    public Message<String> execute(Message<String> message) throws IOException {
        /**
         * 获取参数
         */
        MessageHeaders headers = message.getHeaders();
        String username = (String) headers.get("username");
        String outputFileMainName = (String) headers.get("outputFileMainName");

        /**
         * 构建xmlFilePath
         */
        String scriptFileFullPath = getOutputPath() + username + "_"
                + outputFileMainName + ".bat";

        /**
         * 生成新消息
         */
        String payload = message.getPayload();
        Message<String> result = MessageBuilder.withPayload(payload)
                .copyHeaders(message.getHeaders())
                .setHeader("scriptFileFullPath", scriptFileFullPath).build();

        /**
         * 写入script文件
         */
        File scriptFile = new File(scriptFileFullPath);
        scriptFile.createNewFile();
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(
                new FileOutputStream(scriptFileFullPath)));
        pw.println(payload);
        pw.flush();
        pw.close();

        return result;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

}
