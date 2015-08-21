package com.cn.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户_权限对应关系列表
 */
@Entity
@Table(name = "userbase_authority")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserBase_Authority {

    /**
     * 用户_权限对应关系ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "userBase_authority_Id", length = 32)
    private String userBase_authority_Id;

    /**
     * 对应关系所属的用户
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private UserBase userBase;

    /**
     * 对应关系所属的权限
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "authorityId")
    private Authority authority;

    /**
     * 构造函数
     */
    public UserBase_Authority() {
        super();
    }

    public Authority getAuthority() {
        return authority;
    }

    public UserBase getUserBase() {
        return userBase;
    }

    public String getUserBase_authority_Id() {
        return userBase_authority_Id;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public void setUserBase(UserBase userBase) {
        this.userBase = userBase;
    }

    public void setUserBase_authority_Id(String userBase_authority_Id) {
        this.userBase_authority_Id = userBase_authority_Id;
    }
}
