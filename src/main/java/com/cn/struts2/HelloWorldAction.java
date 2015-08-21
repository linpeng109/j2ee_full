package com.cn.struts2;

import com.opensymphony.xwork2.Action;
import org.apache.log4j.Logger;

/**
 * Created by SUN on 2015/6/12.
 */
public class HelloWorldAction extends BaseAction {
    private Logger logger = Logger.getLogger(HelloWorldAction.class);

    public String hello() {
        logger.debug("HelloWorldAction is SUCCESS!");
        return Action.SUCCESS;
    }
}
