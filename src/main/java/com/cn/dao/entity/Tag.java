package com.cn.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;

@Entity
@Table(name = "tag")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Tag {


    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "tagID", length = 32)
    @DocumentId(name = "tagID")
    private String tagID;

    public String getTagID() {
        return tagID;
    }

    public void setTagID(String tagID) {
        this.tagID = tagID;
    }


}
