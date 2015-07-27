package com.cn.webservice.jaxws;

import org.apache.log4j.Logger;

/**
 * WebService服务接口实现（测试用）
 *
 * @author hartwell
 * @version 1.0
 */
public class HelloWorldWebServiceImpl implements HelloWorldWebService {

    public Logger logger = Logger.getLogger(HelloWorldWebServiceImpl.class);

    @Override
    public String helloWorldByString(String hi) {
        String result = "Hello! " + hi;
        logger.debug(result);
        return result;
    }

}
