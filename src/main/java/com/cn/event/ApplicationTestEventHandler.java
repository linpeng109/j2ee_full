package com.cn.event;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;

public class ApplicationTestEventHandler {
    public Logger logger = Logger.getLogger(ApplicationTestEventHandler.class);

    public String messageHandler(ApplicationEvent event) {
        return "获取传输数据：" + event.getSource();
    }
}
