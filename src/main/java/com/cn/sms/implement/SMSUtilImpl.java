package com.cn.sms.implement;

import com.cn.sms.SMSUtil;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class SMSUtilImpl implements SMSUtil {

    public Logger logger = Logger.getLogger(SMSUtilImpl.class);

    public Properties properties;
    public String urlString;

    public Properties getProperties() {
        return properties;
    }

    public String getUrlString() {
        return urlString;
    }

    @Override
    public String sendSMS(String phone, String message) throws IOException {
        // 构建参数串
        StringBuffer buffer = new StringBuffer();
        for (Object key : properties.keySet()) {
            String keyString = (String) key;
            buffer.append(keyString);
            buffer.append("=");
            buffer.append(properties.getProperty(keyString));
            buffer.append("&");
        }
        buffer.append("phone=" + phone);
        buffer.append("&message=" + message);
        logger.debug(urlString + "?" + buffer.toString());
        // 建立http链接
        URL url = new URL(urlString + "?");
        URLConnection urlConnection = url.openConnection();
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        PrintStream output = new PrintStream(new BufferedOutputStream(
                urlConnection.getOutputStream()));
        output.print(buffer.toString());
        output.flush();
        // 建立输出结果
        StringBuffer outputBuffer = new StringBuffer();
        BufferedInputStream input = new BufferedInputStream(
                urlConnection.getInputStream());
        for (; ; ) {
            int data = 0;
            data = input.read();
            if (data == -1) {
                break;
            } else {
                outputBuffer.append((char) data);
            }
        }
        // 关闭输入/输出流
        input.close();
        output.close();
        // 输出结果
        return outputBuffer.toString();
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

}
