package com.cn.common.implement;

import com.cn.common.PropertiesModule;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * Property文件处理模块
 *
 * @author Administrator
 */
public class PropertiesModuleImpl implements PropertiesModule {

    /**
     * 日志
     */
    public static Logger logger = Logger.getLogger(PropertiesModuleImpl.class);

    /**
     * 测试函数
     *
     * @param args 测试参数
     * @throws IOException IO异常
     */
    public static void main(String[] args) throws IOException {
        PropertiesModule propertiesModuleImpl = new PropertiesModuleImpl();
        Properties properties = new Properties();
        properties.setProperty("name", "123");
        properties.setProperty("age", "345");
        properties.setProperty("address", "Beijing");
        propertiesModuleImpl.writeProperties("d:/info.properties", properties);

        // 获取所有属性值的历遍方法
        // Properties properties = propertiesModuleImpl
        // .getAllProperties("d:/info.properties");
        // Enumeration<String> enume = (Enumeration<String>) properties
        // .propertyNames();
        // while (enume.hasMoreElements()) {
        // String key = enume.nextElement();
        // String value = properties.getProperty(key);
        // logger.debug(key + "=" + value);
        // }

    }

    /**
     * 默认属性
     */
    public Properties defaultProperties;

    /**
     * checkFileIsExist 检查文件是否存在
     *
     * @param sourceName 带文件路径的文件名
     * @return boolean
     */
    public boolean checkFileIsExist(String sourceName) {
        File file = new File(sourceName);
        if (file.exists() && file.isFile()) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * 取得所有的属性名和属性值
     *
     * @see
     * com.cn.common.implement.PropertiesFileModule#getAllProperties(java.lang
     * .String)
     */
    @Override
    public Properties getAllProperties(String filePath) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStream inputStream = new BufferedInputStream(fileInputStream);
        properties.load(inputStream);
        return properties;
    }

    /*
     * 得到默认的properties赋值
     *
     * @see com.cn.common.implement.PropertiesFileModule#getDefaultProperties()
     */
    @Override
    public Properties getDefaultProperties() {
        return defaultProperties;
    }

    /*
     * 根据所给的key得到value值
     *
     * @see
     * com.cn.common.implement.PropertiesFileModule#getPropertiesByKey(java.
     * lang.String, java.lang.String)
     */
    @Override
    public String getPropertiesByKey(String filePath, String key)
            throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        InputStream inputStream = new BufferedInputStream(fileInputStream);
        properties.load(inputStream);
        return properties.getProperty(key);
    }

    public void setDefaultProperties(Properties defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    /*
     * 根据所给的properties改写或重写properties文件
     *
     * @see
     * com.cn.common.implement.PropertiesFileModule#writeProperties(java.lang
     * .String, java.util.Properties)
     */
    @Override
    public void writeProperties(String filePath, Properties properties)
            throws IOException {
        // InputStream in = new FileInputStream(filePath);
        // properties.load(in);
        OutputStream out = new FileOutputStream(filePath);
        properties.store(out, "Rewrite All Properties value");
    }

    /*
     * 根据key值改写或添加该key值的value
     *
     * @see
     * com.cn.common.implement.PropertiesFileModule#writePropertiesByKey(java
     * .lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public void writePropertiesByKey(String filePath, String key, String value)
            throws IOException {
        Properties properties = new Properties();
        InputStream in = new FileInputStream(filePath);
        properties.load(in);
        OutputStream out = new FileOutputStream(filePath);
        properties.setProperty(key, value);
        properties.setProperty("age", "123");
        properties.store(out, "Update '" + key + "' value");
    }
}
