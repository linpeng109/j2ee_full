package com.cn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 权限列表
 */
@Entity
@Table(name = "authority")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority {

	/**
	 * 权限ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "authorityId", length = 32)
	private String authorityId;

	/**
	 * 权限描述
	 */
	@Column(name = "authorityDescription", length = 200)
	private String authorityDescription;

	/**
	 * 权限字符串/索引
	 */
	@Column(name = "authorityString", length = 100)
	private String authorityString;

	/**
	 * 权限过期日期
	 */
	@Column(name = "authorityExpireDate")
	private Date authorityExpireDate;

	/**
	 * 权限状态
	 */
	@Column(name = "authorityStatus")
	private int authorityStatus;

	/**
	 * 用户_权限对应关系组/权限删除则用户_权限对应关系删除/延时加载
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "authority", fetch = FetchType.LAZY)
	private List<UserBase_Authority> userBase_authority = new ArrayList<UserBase_Authority>();

	/**
	 * 构造函数
	 */
	public Authority() {
		super();
		GregorianCalendar calendar = new GregorianCalendar(2030, 12, 30);
		this.authorityExpireDate = (calendar).getTime();
	}

	public String getAuthorityDescription() {
		return authorityDescription;
	}

	public Date getAuthorityExpireDate() {
		return authorityExpireDate;
	}

	public String getAuthorityId() {
		return authorityId;
	}

	public int getAuthorityStatus() {
		return authorityStatus;
	}

	public String getAuthorityString() {
		return authorityString;
	}

	public List<UserBase_Authority> getUserBase_authority() {
		return userBase_authority;
	}

	public void setAuthorityDescription(String authorityDescription) {
		this.authorityDescription = authorityDescription;
	}

	public void setAuthorityExpireDate(Date authorityExpireDate) {
		this.authorityExpireDate = authorityExpireDate;
	}

	public void setAuthorityId(String authorityId) {
		this.authorityId = authorityId;
	}

	public void setAuthorityStatus(int authorityStatus) {
		this.authorityStatus = authorityStatus;
	}

	public void setAuthorityString(String authorityString) {
		this.authorityString = authorityString;
	}

	public void setUserBase_authority(
			List<UserBase_Authority> userBase_authority) {
		this.userBase_authority = userBase_authority;
	}

}
