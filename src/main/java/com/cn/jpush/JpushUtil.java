package com.cn.jpush;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jupiter on 16-2-26.
 */
public class JpushUtil {


    private String appkey;
    private String masterSecret;

    public static void main(String[] params) throws APIConnectionException, APIRequestException {
        JpushUtil jpushUtil=new JpushUtil();
        jpushUtil.sendNotificationAll();

    }

    public void sendNotificationAll() throws APIConnectionException, APIRequestException {
        JPushClient jPushClient = new JPushClient(masterSecret, appkey);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String formatStr = formatter.format(new Date());
        String alert = "极光推送测试，时间：" + formatStr;
        jPushClient.sendNotificationAll(alert);
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getMasterSecret() {
        return masterSecret;
    }

    public void setMasterSecret(String masterSecret) {
        this.masterSecret = masterSecret;
    }


}
