package com.yhq.model;

import java.util.Date;

import javax.persistence.*;

/**
 * Book 实体类
 * Wed Dec 28 22:13:19 CST 2016 zlm
 */ 
@Entity
@Table(name = "ssf_book")
public class Book{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column
	private String name;

	@Column
	private String author;

	@Column
	private String intro;

	@Column
	private Double price;

	@Column
	private Long type;

	@Column
	private String cover;

	@Column
	private String picture;

	@Column
	private String Introduction;

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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public String getAuthor(){
		return author;
	}

	public void setIntro(String intro){
		this.intro = intro;
	}

	public String getIntro(){
		return intro;
	}

	public void setPrice(Double price){
		this.price = price;
	}

	public Double getPrice(){
		return price;
	}

	public void setType(Long type){
		this.type = type;
	}

	public Long getType(){
		return type;
	}

	public void setCover(String cover){
		this.cover = cover;
	}

	public String getCover(){
		return cover;
	}

	public void setPicture(String picture){
		this.picture = picture;
	}

	public String getPicture(){
		return picture;
	}

	public void setIntroduction(String Introduction){
		this.Introduction = Introduction;
	}

	public String getIntroduction(){
		return Introduction;
	}

}

