package com.cn.struts2;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SUN on 2015/6/12.
 */
public class HelloAction extends BaseAction {
    private Logger logger = Logger.getLogger(HelloAction.class);
    public String helloParameter;
    private Map hashMap = new HashMap();

    public Map getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map hashMap) {
        this.hashMap = hashMap;
    }

    public String getHelloParameter() {
        return helloParameter;
    }

    public void setHelloParameter(String helloParameter) {
        this.helloParameter = helloParameter;
    }

    public String hello() {

        logger.debug("HelloAction is SUCCESS!");
        hashMap.put("hello", helloParameter);
        display(hashMap);
        return super.SUCCESS;
    }

    private void display(Map<String, String> map) {
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            logger.debug(String.format("[%s = %s]", key, value));
        }
    }

}
