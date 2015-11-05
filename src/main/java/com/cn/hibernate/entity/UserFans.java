package com.cn.hibernate.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "userfans")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class UserFans extends UserBase {


    /**
     * 用户所对应的用户_权限对应关系表/用户删除则用户_权限对应关系删除/延时加载
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "userFans", fetch = FetchType.LAZY)
    private List<Transcation> transcations = new ArrayList<Transcation>();

    public List<Transcation> getTranscations() {
        return transcations;
    }

    public void setTranscations(List<Transcation> transcations) {
        this.transcations = transcations;
    }


}
