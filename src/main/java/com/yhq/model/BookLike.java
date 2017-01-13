package com.yhq.model;

import java.util.Date;

import javax.persistence.*;

/**
 * BookLike 实体类
 * Thu Jan 12 22:04:42 CST 2017 zlm
 */ 
@Entity
@Table(name = "ssf_book_like")
public class BookLike{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "book_id")
	private Long bookId;

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

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return userId;
	}

	public void setBookId(Long bookId){
		this.bookId = bookId;
	}

	public Long getBookId(){
		return bookId;
	}

}

