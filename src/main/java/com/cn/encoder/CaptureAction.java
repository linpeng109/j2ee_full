package com.cn.encoder;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.text.DecimalFormat;

public class CaptureAction {

    /**
     * 依赖注入获取命令和参数行
     */
    private String parameterLine;
    private String command;
    private int captureTimes;

    private String commandLine;

    public Message<String> execute(Message<String> message) throws Exception {

        /**
         * 获取参数
         */
        MessageHeaders headers = message.getHeaders();
        String outputPathAndOutputFileMainName = (String) headers
                .get("outputPathAndOutputFileMainName");
        String lengthString = (String) headers.get("ID_LENGTH");
        double length = Double.parseDouble(lengthString);

        /**
         * 处理命令行
         */
        commandLine = parameterLine.replaceAll(":command:", command)
                .replaceAll(":outputPathAndOutputFileMainName:",
                        outputPathAndOutputFileMainName);
        String newCommandLine = "";
        for (int i = 0; i < captureTimes; ++i) {
            String currentCommandLine = commandLine.replaceAll(":i:",
                    String.valueOf(i));
            double time = i * length / captureTimes;
            currentCommandLine = currentCommandLine.replaceAll(":time:",
                    round_half_up(time));
            newCommandLine = newCommandLine + currentCommandLine + "\n";
        }

        /**
         * 生成新消息
         */
        String payload = message.getPayload();
        Message<String> result = MessageBuilder
                .withPayload(payload + newCommandLine + "\n")
                .copyHeaders(headers).setHeader("captureTimes", captureTimes)
                .build();

        return result;
    }

    /**
     * 四舍五入函数
     *
     * @param input 待处理小数
     * @return 处理后字符串
     */
    public static String round_half_up(double input) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String output = decimalFormat.format(input);
        return output;
    }

    public int getCaptureTimes() {
        return captureTimes;
    }

    public String getCommand() {
        return command;
    }

    public String getParameterLine() {
        return parameterLine;
    }

    public void setCaptureTimes(int captureTimes) {
        this.captureTimes = captureTimes;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setParameterLine(String parameterLine) {
        this.parameterLine = parameterLine;
    }

}
