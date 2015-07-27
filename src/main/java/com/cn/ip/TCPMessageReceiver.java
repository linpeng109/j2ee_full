package com.cn.ip;

import org.apache.log4j.Logger;

public class TCPMessageReceiver {
    private Logger logger = Logger.getLogger(TCPMessageReceiver.class);

    public TCPMessageReceiver() {
    }

    public String receiver(byte[] body) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < body.length; ++i) {
            result.append(body[i]);
        }
        logger.debug(result.toString());
        return result.toString();
    }
}
