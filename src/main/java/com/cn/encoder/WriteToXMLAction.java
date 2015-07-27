package com.cn.encoder;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

public class WriteToXMLAction {

    /**
     * 输出路径
     */
    private String outputPath;

    public Message<String> execute(Message<String> message) throws IOException {

        /**
         * 获取参数
         */
        MessageHeaders headers = message.getHeaders();
        String username = (String) headers.get("username");
        String outputFileMainName = (String) headers.get("outputFileMainName");


        /**
         * 编码结束时间
         */
        Date currentDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String endTime = format.format(currentDate);


        /**
         * 生成新消息
         */
        String payload = message.getPayload();
        Message<String> result = MessageBuilder.withPayload(payload)
                .copyHeaders(headers).setHeader("payload", payload)
                .setHeader("endTime", endTime).build();

        /**
         * 构建xmlFilePath
         */
        String xmlFilePath = getOutputPath() + username + "_"
                + outputFileMainName + ".xml";
        File xmlFile = new File(xmlFilePath);

        /**
         * 设置xml格式
         */
        OutputFormat outPutFormat = OutputFormat.createPrettyPrint();
        outPutFormat.setEncoding("UTF8");
        XMLWriter xmlwriter = new XMLWriter(new FileOutputStream(xmlFile),
                outPutFormat);

        /**
         * 转换xml
         */
        Document xmlDocument = convertExecutionContextToXMLDocument(result);

        /**
         * 写入xml文件
         */
        xmlwriter.write(xmlDocument);
        xmlwriter.close();

        return result;
    }

    /**
     * 将ExecutionContext中的变量转换为xml文件
     *
     * @param message 待处理消息
     * @return 处理后消息
     */
    public Document convertExecutionContextToXMLDocument(Message<String> message) {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("media");

        Iterator<?> it = message.getHeaders().entrySet().iterator();
        while (it.hasNext()) {
            Entry<?, ?> entry = (Entry<?, ?>) it.next();
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            root.addElement(key).addText(value);
        }
        return document;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

}
