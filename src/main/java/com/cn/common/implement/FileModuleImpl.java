package com.cn.common.implement;

import com.cn.common.FileModule;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作模块
 *
 * @author Administrator
 */
public class FileModuleImpl implements FileModule {

    public String pathString;

    private File path;
    public String extension;

    public FileModuleImpl() {

    }

    public String getExtension() {
        return extension;
    }

    // 文件扩展名过滤器：过滤指定扩展名的非目录文件
    public FilenameFilter getFileExtensionFilter(String extension) {
        final String _extension = extension;
        return new FilenameFilter() {
            @Override
            public boolean accept(File file, String name) {
                boolean result = name.endsWith(_extension);
                return result;
            }
        };
    }

    /*
     * 按过滤器要求列出文件列表
     */
    @Override
    public List<File> getFileList() {
        path = new File(pathString);
        File[] files = path.listFiles(getFileExtensionFilter(extension));
        int size = files.length;
        List<File> fileList = new ArrayList<File>();
        for (int i = 0; i < size; ++i) {
            fileList.add(files[i]);
        }
        return fileList;

    }

    public String getPathString() {
        return pathString;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setPathString(String pathString) {
        this.pathString = pathString;
    }
}
