package com.cn.common;

import java.io.IOException;
import java.util.Properties;

public interface PropertiesModule {

    /**
     * 读取properties的全部信息
     *
     * @param filePath 文件全路径
     * @return 返回Properties属性值
     * @throws IOException IO异常
     */
    public abstract Properties getAllProperties(String filePath)
            throws IOException;

    public abstract Properties getDefaultProperties();

    /**
     * 根据key读取value
     *
     * @param filePath 文件全路径
     * @param key      属性名
     * @return 返回key值
     * @throws IOException IO异常
     */
    public abstract String getPropertiesByKey(String filePath, String key)
            throws IOException;

    /**
     * 写入properties信息
     *
     * @param filePath   文件全路径
     * @param properties 属性名值对
     * @throws IOException IO异常
     */
    public abstract void writeProperties(String filePath, Properties properties)
            throws IOException;

    /**
     * 写入properties信息
     *
     * @param filePath 文件全路径
     * @param key      属性名
     * @param value    属性值
     * @throws IOException IO异常
     */
    public abstract void writePropertiesByKey(String filePath, String key,
                                              String value) throws IOException;

}