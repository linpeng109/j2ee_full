package com.cn.webservice.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Webservice服务接口
 *
 * @author Hartwell
 * @version 3.0
 */
@WebService(targetNamespace = "com.cn.webservice", name = "hwlloworld")
public interface HelloWorldWebService {
    @WebMethod
    public String helloWorldByString(String hi);
}