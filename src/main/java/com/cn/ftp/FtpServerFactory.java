package com.cn.ftp;

import org.apache.ftpserver.FtpServer;
import org.apache.log4j.Logger;

public class FtpServerFactory {
    private Logger logger = Logger.getLogger(FtpServerFactory.class);
    private FtpServer server;

    public void ftpServerDestroyed() {
        if (server != null) {
            server.stop();
            logger.debug("FtpServer stopped");
        } else {
            logger.debug("No running FtpServer found");
        }
    }

    public void ftpServerInitialized() {
        try {
            server.start();
            logger.debug("FtpServer started");
        } catch (Exception e) {
            throw new RuntimeException("Failed to start FtpServer", e);
        }
    }

    public FtpServer getServer() {
        return server;
    }

    public void setServer(FtpServer server) {
        this.server = server;
    }
}
