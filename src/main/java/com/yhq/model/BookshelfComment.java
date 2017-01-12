package com.yhq.model;

import java.util.Date;

import javax.persistence.*;

/**
 * BookshelfComment 实体类
 * Thu Jan 12 23:12:40 CST 2017 zlm
 */ 
@Entity
@Table(name = "ssf_bookshelf_comment")
public class BookshelfComment{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column(name = "comment_id")
	private Long commentId;

	@Column(name = "bookshelf_id")
	private Long bookshelfId;

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

	public void setBookshelfId(Long bookshelfId){
		this.bookshelfId = bookshelfId;
	}

	public Long getBookshelfId(){
		return bookshelfId;
	}

}

