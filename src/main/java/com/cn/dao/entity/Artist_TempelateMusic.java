package com.cn.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;

/**
 * 艺人-模板歌曲对应关系列表
 */
@Entity
@Table(name = "artist_tempelatemusic")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Artist_TempelateMusic {

    /**
     * ID
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "artist_tempelateMusicID", length = 32)
    @DocumentId(name = "artist_tempelateMusicID")
    private String artist_tempelateMusicID;


    /**
     * 对应关系所属的用户
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "artistId")
    private Artist artist;

    /**
     * 对应关系所属的权限
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "tempelateMusicId")
    private TempelateMusic tempelateMusic;

    public String getArtist_tempelateMusicID() {
        return artist_tempelateMusicID;
    }

    public Artist getArtist() {
        return artist;
    }

    public TempelateMusic getTempelateMusic() {
        return tempelateMusic;
    }

    public void setArtist_tempelateMusicID(String artist_tempelateMusicID) {
        this.artist_tempelateMusicID = artist_tempelateMusicID;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setTempelateMusic(TempelateMusic tempelateMusic) {
        this.tempelateMusic = tempelateMusic;
    }


}
