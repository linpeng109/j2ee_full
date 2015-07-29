package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "userfans")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class UserFans extends UserBase {
	
	
	/**
	 * 用户所对应的用户_权限对应关系表/用户删除则用户_权限对应关系删除/延时加载
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "userFans", fetch = FetchType.LAZY)
	private List<Transcation> transcations = new ArrayList<Transcation>();

	public List<Transcation> getTranscations() {
		return transcations;
	}

	public void setTranscations(List<Transcation> transcations) {
		this.transcations = transcations;
	}
	
	
	

}
