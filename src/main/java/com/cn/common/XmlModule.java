package com.cn.common;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface XmlModule {
    /**
     * addAttribute 加入元素的属性
     *
     * @param document       Document
     * @param xPath          String
     * @param attributeName  String
     * @param attributeValue String
     * @return Document
     * @throws DocumentException 解析错误
     */
    public Document addAttribute(Document document, String xPath,
                                 String attributeName, String attributeValue)
            throws DocumentException;

    /**
     * addElement 加入元素
     *
     * @param document     Document
     * @param xPath        String
     * @param elementName  String
     * @param elementValue String
     * @return Document
     * @throws DocumentException 解析错误
     */
    public Document addElement(Document document, String xPath,
                               String elementName, String elementValue) throws DocumentException;

    /**
     * addElementAndAttribute
     *
     * @param document       Document
     * @param xPath          String
     * @param elementName    String
     * @param elementValue   String
     * @param attributeName  String
     * @param attributeValue String
     * @return Document
     */
    public Document addElementAndAttribute(Document document, String xPath,
                                           String elementName, String elementValue, String attributeName,
                                           String attributeValue);

    /**
     * createXml 创建xml文件
     *
     * @param root String
     * @return Document
     * @throws IOException io错误
     */
    public Document createXml(String root) throws IOException;

    /**
     * readAttributes 读取xml属性
     *
     * @param document String
     * @param xPath    String
     * @return List
     */
    public List<Attribute> readAttributes(Document document, String xPath);

    /**
     * readAttributes 读取xml属性
     *
     * @param filename String
     * @param xPath    String
     * @return List
     * @throws DocumentException 解析错误
     */
    public List<Attribute> readAttributes(String filename, String xPath)
            throws DocumentException;

    /**
     * readDocument 读取xml文件
     *
     * @param file String
     * @return Document
     * @throws DocumentException 解析错误
     */
    public Document readDocument(File file) throws DocumentException;

    /**
     * readDocument 读取xml文件
     *
     * @param filename String
     * @return Document
     * @throws DocumentException 解析错误
     */
    public Document readDocument(String filename) throws DocumentException;

    /**
     * readElements 读取xml元素
     *
     * @param document String
     * @param xPath    String
     * @return List
     */
    public List<Element> readElements(Document document, String xPath);

    /**
     * readElements 读取xml元素
     *
     * @param filename String
     * @param xPath    String
     * @return List
     * @throws DocumentException 解析错误
     */
    public List<Element> readElements(String filename, String xPath)
            throws DocumentException;

    /**
     * writeXml 写入xml文件
     *
     * @param filename String
     * @param document Document
     * @throws IOException IO错误
     */
    public void writeXml(String filename, Document document) throws IOException;
}
