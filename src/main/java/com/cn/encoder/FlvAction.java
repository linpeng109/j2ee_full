package com.cn.encoder;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

public class FlvAction {

    /**
     * 依赖注入获取命令和参数行
     */
    private String parameterLine;
    private String command;
    private String commandLine;

    public Message<String> execute(Message<String> message) {
        /**
         * 获取参数
         */
        MessageHeaders headers = message.getHeaders();
        String input = (String) headers.get("input");
        String outputPathAndOutputFileMainName = (String) headers
                .get("outputPathAndOutputFileMainName");

        /**
         * 处理命令行
         */
        commandLine = parameterLine
                .replaceAll(":command:", command)
                .replaceAll(":input:", input)
                .replaceAll(":outputPathAndOutputFileMainName:",
                        outputPathAndOutputFileMainName);

        /**
         * 生成新消息
         */
        String payload = message.getPayload();
        Message<String> result = MessageBuilder
                .withPayload(payload + commandLine + "\n").copyHeaders(headers)
                .build();

        return result;
    }

    public String getCommand() {
        return command;
    }

    public String getParameterLine() {
        return parameterLine;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setParameterLine(String parameterLine) {
        this.parameterLine = parameterLine;
    }

}
