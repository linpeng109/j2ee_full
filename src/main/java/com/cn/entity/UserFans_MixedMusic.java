package com.cn.entity;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;


@Entity
@Table(name = "userfans_mixedmusic")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserFans_MixedMusic {
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "userFans_MixedMusicID", length = 32)
	@DocumentId(name = "userFans_MixedMusicID")
	private String userFans_MixedMusicID;
	
	
	/**
	 * 对应关系所属的用户
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userId")
	private UserFans userFans;

	/**
	 * 对应关系所属的权限
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "mixedMusicID")
	private MixedMusic mixedMusic;

	public String getUserFans_MixedMusicID() {
		return userFans_MixedMusicID;
	}

	public UserFans getUserFans() {
		return userFans;
	}

	public MixedMusic getMixedMusic() {
		return mixedMusic;
	}

	public void setUserFans_MixedMusicID(String userFans_MixedMusicID) {
		this.userFans_MixedMusicID = userFans_MixedMusicID;
	}

	public void setUserFans(UserFans userFans) {
		this.userFans = userFans;
	}

	public void setMixedMusic(MixedMusic mixedMusic) {
		this.mixedMusic = mixedMusic;
	}
	
	
	
	

}
