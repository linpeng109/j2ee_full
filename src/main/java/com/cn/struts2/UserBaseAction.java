package com.cn.struts2;

import com.cn.hibernate.entity.UserBase;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by linpeng109 on 15-8-25.
 */
public class UserBaseAction extends BaseAction {
    final Logger logger = Logger.getLogger(UserBaseAction.class);

    public int pageSize;
    public int pageNum;
    public boolean isCache;

    public List<UserBase> list;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<UserBase> getList() {
        return list;
    }

    public void setList(List<UserBase> list) {
        this.list = list;
    }

    public String execute() {
        logger.debug(pageNum + ";" + pageSize);
        setList(hibernateDAO.listByPage(UserBase.class, pageSize, pageNum, true));
        return super.SUCCESS;
    }
}
