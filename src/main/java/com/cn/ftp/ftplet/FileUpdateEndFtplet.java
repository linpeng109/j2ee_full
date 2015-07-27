package com.cn.ftp.ftplet;

import org.apache.ftpserver.ftplet.*;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.io.IOException;
import java.util.HashMap;

/**
 * 文件上传完成后的处理函数
 *
 * @author admin
 */
public class FileUpdateEndFtplet extends DefaultFtplet implements
        ApplicationContextAware {

    public MessageChannel filePathChannel;

    /**
     * 日志
     */
    public Logger logger = Logger.getLogger(FileUpdateEndFtplet.class);

    /**
     * 获取spring容器
     */
    public ApplicationContext applicationContext;

    private String homeDirectory;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public MessageChannel getFilePathChannel() {
        return filePathChannel;
    }

    public String getHomeDirectory() {
        return homeDirectory;
    }

    /*
     * 建立连接时动作
     *
     * @see
     * org.apache.ftpserver.ftplet.DefaultFtplet#onConnect(org.apache.ftpserver
     * .ftplet.FtpSession)
     */
    @Override
    public FtpletResult onConnect(FtpSession session) {
        return FtpletResult.DEFAULT;
    }

    @Override
    public FtpletResult onDisconnect(FtpSession session) throws FtpException,
            IOException {
        return super.onDisconnect(session);
    }

    /*
     * 下载完成时动作
     *
     * @see
     * org.apache.ftpserver.ftplet.DefaultFtplet#onDownloadEnd(org.apache.ftpserver
     * .ftplet.FtpSession, org.apache.ftpserver.ftplet.FtpRequest)
     */
    @Override
    public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) {

        return FtpletResult.DEFAULT;
    }

    /*
     * 用户登录时动作
     *
     * @see
     * org.apache.ftpserver.ftplet.DefaultFtplet#onLogin(org.apache.ftpserver
     * .ftplet.FtpSession, org.apache.ftpserver.ftplet.FtpRequest)
     */
    @Override
    public FtpletResult onLogin(FtpSession session, FtpRequest request) {
        return FtpletResult.DEFAULT;
    }

    /*
     * 上传完成时动作：通过spring容器发送事件消息
     *
     * @see
     * org.apache.ftpserver.ftplet.DefaultFtplet#onUploadEnd(org.apache.ftpserver
     * .ftplet.FtpSession, org.apache.ftpserver.ftplet.FtpRequest)
     */
    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request) {
        String username = session.getUser().getName();
        String input = session.getUser().getHomeDirectory() + "/"
                + request.getArgument();
        String homeDirectory = session.getUser().getHomeDirectory();
        /**
         * 发送spring事件
         */
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("username", username);
        map.put("homeDirectory", homeDirectory);
        map.put("input", input);
        logger.debug(map);

        Message<String> message = MessageBuilder.withPayload("")
                .copyHeaders(map).build();

        filePathChannel.send(message);

        return FtpletResult.DEFAULT;
    }

    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        this.applicationContext = arg0;
    }

    public void setFilePathChannel(MessageChannel filePathChannel) {
        this.filePathChannel = filePathChannel;
    }

    public void setHomeDirectory(String homeDirectory) {
        this.homeDirectory = homeDirectory;
    }

}
