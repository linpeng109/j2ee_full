package com.cn.common;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * 文件名及文件路径处理接口
 *
 * @author LinPeng
 * @version 1.0
 */
public interface FileNameModule {
    /**
     * checkFileIsExist 检查文件是否存在
     *
     * @param sourceName 源文件名
     * @return boolean 返回是否
     */
    public boolean checkFileIsExist(String sourceName);

    /**
     * checkFilePath 文件路径检查
     *
     * @param sourceName 源文件名
     * @throws FileNotFoundException 文件未找到异常
     */
    public void checkFilePath(String sourceName) throws FileNotFoundException;

    /**
     * getFileExtendName 获取文件扩展名
     *
     * @param sourceName String 输入带文件路径的文件名
     * @return String 返回文件扩展名
     * @throws FileNotFoundException 文件未找到异常
     */
    public String getFileExtendName(String sourceName)
            throws FileNotFoundException;

    /**
     * getFileMainName 获取文件的主文件名
     *
     * @param sourceName String 输入带文件路径的文件名
     * @return String 返回文件的主文件名
     * @throws FileNotFoundException 文件未找到异常
     */
    public String getFileMainName(String sourceName)
            throws FileNotFoundException;

    /**
     * getFileName 获取文件名称
     *
     * @param sourceName String 输入带文件路径的文件名
     * @return String 返回文件的文件名
     * @throws FileNotFoundException 文件未找到异常
     */
    public String getFileName(String sourceName) throws FileNotFoundException;

    /**
     * getFilePath 获得文件路径
     *
     * @param sourceName String 输入带文件路径的文件名
     * @return String 返回文件路径
     * @throws FileNotFoundException 文件未找到异常
     */
    public String getFilePath(String sourceName) throws FileNotFoundException;

    public String getInputBase64(String sourceName)
            throws FileNotFoundException, UnsupportedEncodingException;

    /**
     * getPathAndFimeMainName 获得文件路径和主文件名
     *
     * @param sourceName String
     * @return String 返回文件全路径
     * @throws FileNotFoundException 文件未找到异常
     */
    public String getPathAndFileMainName(String sourceName)
            throws FileNotFoundException;
}
