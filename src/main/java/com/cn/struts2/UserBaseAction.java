package com.cn.struts2;

import com.cn.dao.entity.UserBase;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by linpeng109 on 15-8-25.
 */
public class UserBaseAction extends BaseAction{
    final Logger logger = Logger.getLogger(UserBaseAction.class);

    public List<UserBase> list;

    public List<UserBase> getList() {
        return list;
    }

    public void setList(List<UserBase> list) {
        this.list = list;
    }

    public String execute() {
        setList(hibernateDAO.listByPage(UserBase.class, 20, 0, true));
        return super.SUCCESS;
    }
}
