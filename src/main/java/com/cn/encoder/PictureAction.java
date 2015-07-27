package com.cn.encoder;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.text.DecimalFormat;

public class PictureAction {

    /**
     * 依赖注入获取命令和参数行
     */
    private String parameterLine;
    private String command;
    private String pictureOutputPath;
    private String commandLine;

    public Message<String> execute(Message<String> message) {

        /**
         * 获取参数
         */
        MessageHeaders headers = message.getHeaders();
        String outputPathAndOutputFileMainName = (String) headers
                .get("outputPathAndOutputFileMainName");
        String outputFileMainName = (String) headers.get("outputFileMainName");
        String username = (String) headers.get("username");
        int captureTimes = (int) headers.get("captureTimes");
        String lengthString = (String) headers.get("ID_LENGTH");
        double length = Double.parseDouble(lengthString);

        /**
         * 处理命令行
         */
        commandLine = parameterLine
                .replaceAll(":command:", command)
                .replaceAll(":outputPathAndOutputFileMainName:",
                        outputPathAndOutputFileMainName)
                .replaceAll(":outputFileMainName:", outputFileMainName)
                .replaceAll(":pictureOutputPath:", pictureOutputPath)
                .replaceAll(":username:", username);

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
                .copyHeaders(message.getHeaders())
                .setHeader("captureTimes", captureTimes).build();

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

    public String getCommand() {
        return command;
    }

    public String getParameterLine() {
        return parameterLine;
    }

    public String getPictureOutputPath() {
        return pictureOutputPath;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setParameterLine(String parameterLine) {
        this.parameterLine = parameterLine;
    }

    public void setPictureOutputPath(String pictureOutputPath) {
        this.pictureOutputPath = pictureOutputPath;
    }

}
