package com.cn.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ftpserver")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class FTPServer {

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ftpServerID", length = 32)
    @DocumentId(name = "ftpServerID")
    private String ftpServerID;

    /**
     * 用户所对应的用户_权限对应关系表/用户删除则用户_权限对应关系删除/延时加载
     */
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "ftpServer", fetch = FetchType.LAZY)
    private List<Music> musics = new ArrayList<Music>();


}
