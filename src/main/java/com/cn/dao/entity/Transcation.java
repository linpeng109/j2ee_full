package com.cn.dao.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.search.annotations.DocumentId;

import javax.persistence.*;


@Entity
@Table(name = "transcation")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Inheritance(strategy = InheritanceType.JOINED)
public class Transcation {
	
	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@Column(name = "transcationID", length = 32)
	@DocumentId(name = "transcationID")
	private String transcationID;
	
	/**
	 * 对应关系所属的权限
	 */
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
	@JoinColumn(name = "userFansID")
	private UserFans userFans;

}
