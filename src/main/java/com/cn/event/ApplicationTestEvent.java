package com.cn.event;

import org.springframework.context.ApplicationEvent;

/**
 * Spring测试用事件
 *
 * @author admin
 */
public class ApplicationTestEvent extends ApplicationEvent {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 测试数据
     */
    private String testData;

    /**
     * 构造函数
     *
     * @param source 源对象
     */
    public ApplicationTestEvent(Object source) {
        super(source);
    }

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

}
