package com.cn.hibernate.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "music_tag")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Music_Tag {

    /**
     * 用户_权限对应关系ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "music_tag_Id", length = 32)
    private String music_tag_Id;

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
    private Tag tag;

    public String getMusic_tag_Id() {
        return music_tag_Id;
    }

    public Music getMusic() {
        return music;
    }

    public Tag getTag() {
        return tag;
    }

    public void setMusic_tag_Id(String music_tag_Id) {
        this.music_tag_Id = music_tag_Id;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }


}
