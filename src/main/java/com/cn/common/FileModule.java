package com.cn.common;

import java.io.File;
import java.util.List;

public interface FileModule {

    /**
     * 按要求列出文件列表
     *
     * @return 文件列表
     */
    public List<File> getFileList();

}