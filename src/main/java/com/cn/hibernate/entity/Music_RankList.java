package com.cn.hibernate.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "music_ranklist")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Music_RankList {


    /**
     * 用户_权限对应关系ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "music_ranklist_Id", length = 32)
    private String music_ranklist_Id;

    /**
     * 对应关系所属的用户
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "musicId")
    private Music music;

    /**
     * 对应关系所属的权限
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "ranklistId")
    private RankList ranklist;

    public String getMusic_ranklist_Id() {
        return music_ranklist_Id;
    }

    public Music getMusic() {
        return music;
    }

    public RankList getRanklist() {
        return ranklist;
    }

    public void setMusic_ranklist_Id(String music_ranklist_Id) {
        this.music_ranklist_Id = music_ranklist_Id;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setRanklist(RankList ranklist) {
        this.ranklist = ranklist;
    }


}
