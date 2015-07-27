package com.cn.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;


@Entity
@Table(name = "mixedmusic")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class MixedMusic {
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "mixedMusicID", length = 32)
	@DocumentId(name = "mixedMusicID")
	private String mixedMusicID;

	public String getMixedMusicID() {
		return mixedMusicID;
	}

	public void setMixedMusicID(String mixedMusicID) {
		this.mixedMusicID = mixedMusicID;
	}
	
	
	

}
