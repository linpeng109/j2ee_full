package com.cn.struts2;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SUN on 2015/6/12.
 */
public class WorldAction extends BaseAction {
    private Logger logger = Logger.getLogger(WorldAction.class);
    public String worldParameter;
    private Map hashMap = new HashMap();

    public Map getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map hashMap) {
        this.hashMap = hashMap;
    }

    public String getWorldParameter() {
        return worldParameter;
    }

    public void setWorldParameter(String worldParameter) {
        this.worldParameter = worldParameter;
    }

    public String world() {
        logger.debug("WorldAction is SUCCESS!");
        hashMap.put("world", this.worldParameter);
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
