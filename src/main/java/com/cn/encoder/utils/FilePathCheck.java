package com.cn.encoder.utils;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * 参数检查和命令编译
 *
 * @author admin
 */
public class FilePathCheck {
    /**
     * 日志
     */
    private static Logger logger = Logger.getLogger(FilePathCheck.class);

    /**
     * 静态变量
     */
    public final static boolean CHECK_FAILURE = false;
    public final static boolean CHECK_SUCCESS = true;

    /**
     * 文件检查
     *
     * @param file 待检查的文件全路径
     * @return 文件是否存在
     */
    public static boolean checkFile(String file) {
        if (file == null || file.equals("")
                || (new File(file)).isFile() != true) {
            logger.error("The path parameter (" + file
                    + ") is not to be set currently!");
            return FilePathCheck.CHECK_FAILURE;
        } else {
            return FilePathCheck.CHECK_SUCCESS;
        }
    }

    /**
     * 路径检查
     *
     * @param path 待检查的路径
     * @return 目录是否存在
     */
    public static boolean checkDir(String path) {
        if (path == null || path.equals("")
                || (new File(path)).isDirectory() != true) {
            logger.error("The path parameter (" + path
                    + ") is not to be set currently!");
            return FilePathCheck.CHECK_FAILURE;
        } else {
            return FilePathCheck.CHECK_SUCCESS;
        }
    }
}
