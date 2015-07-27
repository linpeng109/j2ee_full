package com.cn.common;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface FileUploadModule {

    public void uploadFile(HttpServletRequest request) throws IOException;

}