package com.cn.json;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 同感fastjson实现的json工具
 * Created by jupiter on 15-12-22.
 */
public class JSONUtilOverFastJSON {

    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(JSONUtilOverFastJSON.class);

    /**
     * 由json字符串转换为java类的实例
     *
     * @param jsonString json字符串
     * @param clazz      实体类类型
     * @param <T>        实体类
     * @return 实体类实例
     */
    public static <T> Object JSONToObject(String jsonString, Class<T> clazz) {
        T result = JSON.parseObject(jsonString, clazz);
        return result;
    }

    /**
     * 由json字符串转换为java的List类实例
     *
     * @param jsonString jsong字符串
     * @param clazz      实体类类型
     * @param <T>        实体类
     * @return java的list实例
     */
    public static <T> List<T> JSONToArray(String jsonString, Class<T> clazz) {
        List<T> list = JSON.parseArray(jsonString, clazz);
        return list;
    }

    /**
     * 由java类实例转换为json字符串
     *
     * @param object java类实例
     * @return json字符串
     */
    public static String ObjectToJSON(Object object) {
        String result = JSON.toJSONString(object);
        return result;
    }


}
