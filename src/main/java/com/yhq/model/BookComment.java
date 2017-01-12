package com.yhq.model;

import java.util.Date;

import javax.persistence.*;

/**
 * BookComment 实体类
 * Wed Dec 28 22:14:04 CST 2016 zlm
 */ 
@Entity
@Table(name = "ssf_book_comment")
public class BookComment{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "comment_id")
	private Long commentId;

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

	public void setCommentId(Long commentId){
		this.commentId = commentId;
	}

	public Long getCommentId(){
		return commentId;
	}

	public void setBookId(Long bookId){
		this.bookId = bookId;
	}

	public Long getBookId(){
		return bookId;
	}

}

