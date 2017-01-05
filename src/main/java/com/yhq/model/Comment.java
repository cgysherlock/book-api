package com.yhq.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Comment 实体类
 * Mon Jan 02 15:55:31 CST 2017 cgy
 */ 
@Entity
@Table(name = "ssf_comment")
public class Comment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column
	private String content;

	@Column
	private byte score;

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

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setScore(byte score){
		this.score = score;
	}

	public byte getScore(){
		return score;
	}

}

