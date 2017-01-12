package com.yhq.model;

import java.util.Date;

import javax.persistence.*;

/**
 * BookshelfLike 实体类
 * Thu Jan 12 22:28:56 CST 2017 zlm
 */ 
@Entity
@Table(name = "ssf_bookshelf_like")
public class BookshelfLike{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "bookshelf_id")
	private Long bookshelfId;

	@Column(name = "user_id")
	private Long userId;

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

	public void setBookshelfId(Long bookshelfId){
		this.bookshelfId = bookshelfId;
	}

	public Long getBookshelfId(){
		return bookshelfId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return userId;
	}

}

