package com.cn.json;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;

/**
 * Created by jupiter on 15-12-21.
 */
public class JSONUtilOverJackson {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(JSONUtilOverJackson.class);

    /**
     * 由json字符串转换为java类的实例
     *
     * @param JSONString json字符串
     * @param clazz      java实体类类型
     * @param <T>        实体类
     * @return java对象
     * @throws IOException IO异常
     */
    public static <T> Object JSONToObject(String JSONString, Class<T> clazz) throws IOException {
        T result = null;
        ObjectMapper objectMapper = new ObjectMapper();
        result = objectMapper.readValue(JSONString, clazz);
        return result;
    }

    /**
     * 由java类实例转换为json对象
     *
     * @param object java类实例
     * @param <T>    java类
     * @return json字符串
     * @throws IOException
     * @throws JSONException
     */
    public static <T> String ObjectToJSON(T object) throws IOException, JSONException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(object);
        JSONObject result = new JSONObject(jsonString);
        return result.toString();
    }

}
