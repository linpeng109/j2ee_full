package com.cn.struts2;

import com.opensymphony.xwork2.ModelDriven;

import java.util.List;

/**
 * Created by jupiter on 15-10-16.
 */
public class RestUserBaseAction extends BaseAction implements ModelDriven<List> {
    @Override
    public List getModel() {
       // List result=hibernateDAO.listByPage(UserBase.class,20,1,true);
        return null;
    }
}
