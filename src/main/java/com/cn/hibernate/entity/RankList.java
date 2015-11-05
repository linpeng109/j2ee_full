package com.cn.hibernate.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;


@Entity
@Table(name = "ranklist")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class RankList {

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "ranklistID", length = 32)
    @DocumentId(name = "ranklistID")
    private String ranklistID;

    public String getRanklistID() {
        return ranklistID;
    }

    public void setRanklistID(String ranklistID) {
        this.ranklistID = ranklistID;
    }


}
