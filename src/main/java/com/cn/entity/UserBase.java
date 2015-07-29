package com.cn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.DocumentId;

/**
 * 用户（基类）列表
 */
@Entity
@Table(name = "userbase", indexes = { @Index(columnList = "userType"),
		@Index(columnList = "createDate") })
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class UserBase {

	/**
	 * 用户ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "userId", length = 32)
	@DocumentId(name = "userId")
	private String userId;

	/**
	 * 从基础信息中同步过来id
	 */
	@Column(name = "jichuId", length = 50)
	private String jichuId;

	/**
	 * 用户类别 Admin 普通管理员,superAdmin 超级管理员,shopAdmin 网点管理员
	 * ,shopBusiness网点业务员,contraAdmin商铺管理员
	 */
	@Column(name = "userType", length = 50)
	private String userType;

	/**
	 * 用户名称
	 */
	@Column(name = "userName", length = 32, unique = true)
	private String userName;

	/**
	 * 用户口令
	 */
	@Column(name = "passWord", length = 32)
	private String passWord;

	/**
	 * 用户创建时间
	 */
	@Column(name = "createDate")
	@Type(type = "java.util.Date")
	private Date createDate;

	/**
	 * 用户是否可用
	 */
	@Column(name = "enabled")
	@Type(type = "java.lang.Boolean")
	private boolean enable;

	/**
	 * 用户是否锁定
	 */
	@Column(name = "nonLocked")
	@Type(type = "java.lang.Boolean")
	private boolean nonLocked;

	/**
	 * FTP写权限
	 */
	@Column(name = "writepermission")
	@Type(type = "java.lang.Boolean")
	private boolean writepermission;

	/**
	 * 用户所对应的用户_权限对应关系表/用户删除则用户_权限对应关系删除/延时加载
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "userBase", fetch = FetchType.LAZY)
	private List<UserBase_Authority> userBase_authoritys = new ArrayList<UserBase_Authority>();

	/**
	 * 构造函数
	 */
	public UserBase() {
		this.enable = true;
		this.nonLocked = true;
		this.createDate = new Date();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public String getPassWord() {
		return passWord;
	}

	public List<UserBase_Authority> getUserBase_authoritys() {
		return userBase_authoritys;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public String getUserType() {
		return userType;
	}

	public boolean isEnable() {
		return enable;
	}

	public boolean isNonLocked() {
		return nonLocked;
	}

	public boolean isWritepermission() {
		return writepermission;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setNonLocked(boolean nonLocked) {
		this.nonLocked = nonLocked;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public void setUserBase_authoritys(
			List<UserBase_Authority> userBase_authoritys) {
		this.userBase_authoritys = userBase_authoritys;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setWritepermission(boolean writepermission) {
		this.writepermission = writepermission;
	}

	public String getJichuId() {
		return jichuId;
	}

	public void setJichuId(String jichuId) {
		this.jichuId = jichuId;
	}

}
