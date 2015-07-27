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
 * �û������ࣩ�б�
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
	 * �û�ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "userId", length = 32)
	@DocumentId(name = "userId")
	private String userId;

	/**
	 * �ӻ�����Ϣ��ͬ������id
	 */
	@Column(name = "jichuId", length = 50)
	private String jichuId;

	/**
	 * �û���� Admin ��ͨ����Ա,superAdmin ��������Ա,shopAdmin �������Ա
	 * ,shopBusiness����ҵ��Ա,contraAdmin���̹���Ա
	 */
	@Column(name = "userType", length = 50)
	private String userType;

	/**
	 * �û�����
	 */
	@Column(name = "userName", length = 32, unique = true)
	private String userName;

	/**
	 * �û�����
	 */
	@Column(name = "passWord", length = 32)
	private String passWord;

	/**
	 * �û�����ʱ��
	 */
	@Column(name = "createDate")
	@Type(type = "java.util.Date")
	private Date createDate;

	/**
	 * �û��Ƿ����
	 */
	@Column(name = "enabled")
	@Type(type = "java.lang.Boolean")
	private boolean enable;

	/**
	 * �û��Ƿ�����
	 */
	@Column(name = "nonLocked")
	@Type(type = "java.lang.Boolean")
	private boolean nonLocked;

	/**
	 * FTPдȨ��
	 */
	@Column(name = "writepermission")
	@Type(type = "java.lang.Boolean")
	private boolean writepermission;

	/**
	 * �û�����Ӧ���û�_Ȩ�޶�Ӧ��ϵ��/�û�ɾ�����û�_Ȩ�޶�Ӧ��ϵɾ��/��ʱ����
	 */
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "userBase", fetch = FetchType.LAZY)
	private List<UserBase_Authority> userBase_authoritys = new ArrayList<UserBase_Authority>();

	/**
	 * ���캯��
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
