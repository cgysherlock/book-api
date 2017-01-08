package com.yhq.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * BookShelfCollection 实体类
 * Mon Jan 02 20:58:48 CST 2017 cgy
 */ 
@Entity
@Table(name = "ssf_bookshelf_collection")
public class BookShelfCollection implements Serializable{

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
	private String description;

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

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
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

