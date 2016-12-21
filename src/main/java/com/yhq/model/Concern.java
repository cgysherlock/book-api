package com.yhq.model;

import java.util.Date;

import javax.persistence.*;

/**
 * Concern 实体类
 * Wed Dec 21 10:09:12 CST 2016 yhq
 */ 
@Entity
@Table(name = "ssf_concern")
public class Concern{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "concerner_id")
	private Long concernerId;

	@Column(name = "concerned_id")
	private Long concernedId;

	public void setId(Long id){
		this.id = id;
	}

	public Long getId(){
		return id;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}

	public Date getCreateDate(){
		return createDate;
	}

	public void setModifyDate(Date modifyDate){
		this.modifyDate = modifyDate;
	}

	public Date getModifyDate(){
		return modifyDate;
	}

	public void setConcernerId(Long concernerId){
		this.concernerId = concernerId;
	}

	public Long getConcernerId(){
		return concernerId;
	}

	public void setConcernedId(Long concernedId){
		this.concernedId = concernedId;
	}

	public Long getConcernedId(){
		return concernedId;
	}

}

