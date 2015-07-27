package com.cn.sms;

import java.io.IOException;

/**
 * 短信发送模块接口
 *
 * @author Administrator
 */
public interface SMSUtil {

    public String sendSMS(String phone, String message) throws IOException;

}