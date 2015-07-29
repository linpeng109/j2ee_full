package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

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
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "ftpServer", fetch = FetchType.LAZY)
	private List<Music> musics = new ArrayList<Music>();
	
	
}
