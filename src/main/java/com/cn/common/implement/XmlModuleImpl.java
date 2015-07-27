package com.cn.common.implement;

import com.cn.common.XmlModule;
import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * xml处理模块实现
 */
public class XmlModuleImpl implements XmlModule {
    public static Logger logger = Logger.getLogger(XmlModuleImpl.class);

    /**
     * main 测试实例
     *
     * @param args String[]
     * @throws DocumentException 文件解析错误
     * @throws IOException       文件读取错误
     */
    public static void main(String[] args) throws DocumentException,
            IOException {
        XmlModuleImpl xmlModule = new XmlModuleImpl();
        Document document = xmlModule.createXml("root");
        xmlModule.addAttribute(document, "//root", "id", "123");
        xmlModule.addElement(document, "//root", "property", "1235");
        xmlModule.addAttribute(document, "//root//property", "name", "first");
        xmlModule.addElement(document, "//root", "property", "12356");
        xmlModule.writeXml("d:\\a.xml", document);
        List<Element> list = xmlModule.readElements("d:\\a.xml",
                "//root//property[@name='second']");
        for (Iterator<Element> it = list.iterator(); it.hasNext(); ) {
            Element element = it.next();
            logger.debug(element.getText());
        }
    }

    /**
     * xPath 搜索路径
     */
    public String xPath;

    /**
     * list 返回结果集
     */
    public List<?> list;

    /**
     * encode 汉字编码
     */
    public String encode;

    /**
     * formatType xml文件输出格式
     */
    public String formatType;

    public XmlModuleImpl() {
    }

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
    @Override
    public Document addAttribute(Document document, String xPath,
                                 String attributeName, String attributeValue)
            throws DocumentException {
        List<?> list = document.selectNodes(xPath);
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            element.addAttribute(attributeName, attributeValue);
        }
        return document;
    }


    /**
     * addElement 加入元素
     *
     * @param document     Document xmlDoc对象
     * @param xPath        String xpath字符串
     * @param elementName  String 属性名
     * @param elementValue String 属性值
     * @return xmlDoc对象
     * @throws DocumentException doc错误
     */
    @Override
    public Document addElement(Document document, String xPath,
                               String elementName, String elementValue) throws DocumentException {
        List<?> list = document.selectNodes(xPath);
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            Element newElement = element.addElement(elementName);
            newElement.setText(elementValue);
        }
        return document;
    }

    /**
     * addElementAndAttribute 加入元素和属性
     *
     * @param document       Document
     * @param xPath          String
     * @param elementName    String
     * @param elementValue   String
     * @param attributeName  String
     * @param attributeValue String
     * @return Document
     */
    @Override
    public Document addElementAndAttribute(Document document, String xPath,
                                           String elementName, String elementValue, String attributeName,
                                           String attributeValue) {

        List<?> list = document.selectNodes(xPath);
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Element element = (Element) iterator.next();
            Element newElement = element.addElement(elementName);
            newElement.setText(elementValue);
            newElement.addAttribute(attributeName, attributeValue);
        }
        return document;
    }

    /**
     * createXml 创建xml文件
     *
     * @param root String
     * @return Document
     * @throws IOException io错误
     */
    @Override
    public Document createXml(String root) throws IOException {
        Document document = DocumentHelper.createDocument();
        document.addElement(root);
        return document;
    }

    public String getEncode() {
        return encode;
    }

    public String getFormatType() {
        return formatType;
    }

    public List<?> getList() {
        return list;
    }

    /**
     * readAttributes 读取xml属性
     *
     * @param document String
     * @param xPath    String
     * @return List
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Attribute> readAttributes(Document document, String xPath) {
        List<Attribute> attributes = document.selectNodes(xPath);
        return attributes;
    }

    /**
     * readAttributes 读取xml属性
     *
     * @param filename String
     * @param xPath    String
     * @return List
     * @throws DocumentException 解析错误
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Attribute> readAttributes(String filename, String xPath)
            throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(filename);
        List<?> attributes = document.selectNodes(xPath);
        return (List<Attribute>) attributes;
    }

    /**
     * readDocument 读取xml文件
     *
     * @param file String
     * @return Document
     * @throws DocumentException 解析错误
     */
    @Override
    public Document readDocument(File file) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(file);
        return document;
    }

    /**
     * readDocument 读取xml文件
     *
     * @param filename String
     * @return Document
     * @throws DocumentException 解析错误
     */
    @Override
    public Document readDocument(String filename) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(filename);
        return document;
    }

    /**
     * readElements 读取xml元素
     *
     * @param document String
     * @param xPath    String
     * @return List
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Element> readElements(Document document, String xPath) {
        List<Element> elements = document.selectNodes(xPath);
        return elements;
    }

    /**
     * readElements 读取xml元素
     *
     * @param filename String
     * @param xPath    String
     * @return List
     * @throws DocumentException 解析错误
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Element> readElements(String filename, String xPath)
            throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(filename);
        List<Element> elements = document.selectNodes(xPath);
        return elements;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public void setFormatType(String formatType) {
        this.formatType = formatType;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    /**
     * writeXml 写入xml文件
     *
     * @param filename String
     * @param document Document
     * @throws IOException io错误
     */
    @Override
    public void writeXml(String filename, Document document) throws IOException {
        OutputFormat format;
        if (this.formatType == "Pretty") {
            format = OutputFormat.createPrettyPrint();
        } else if (this.formatType == "Compact") {
            format = OutputFormat.createCompactFormat();
        } else {
            format = OutputFormat.createCompactFormat();
        }
        format.setEncoding(encode);
        XMLWriter writer = new XMLWriter(new FileWriter(new File(filename)),
                format);
        writer.write(document);
        writer.close();
    }

}
