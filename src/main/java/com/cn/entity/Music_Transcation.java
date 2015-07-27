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
@Table(name = "music_transcation")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Music_Transcation {
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "music_transcationID", length = 32)
	@DocumentId(name = "music_transcationID")
	private String music_transcationID;
	
	
	/**
	 * ��Ӧ��ϵ�������û�
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "musicId")
	private Music music;

	/**
	 * ��Ӧ��ϵ������Ȩ��
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "transcationId")
	private Transcation transcation;

	public String getMusic_transcationID() {
		return music_transcationID;
	}

	public Music getMusic() {
		return music;
	}

	public Transcation getTranscation() {
		return transcation;
	}

	public void setMusic_transcationID(String music_transcationID) {
		this.music_transcationID = music_transcationID;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public void setTranscation(Transcation transcation) {
		this.transcation = transcation;
	}

	
	
}
