package com.yhq.model;

import java.util.Date;

   /**
    * picture 实体类
    * Wed Nov 16 11:26:04 CST 2016 yhq
    */ 


public class Picture{
	private long id;
	private String name;
	private String fname;
	private long userId;
	private String intro;
	private String tags;
	private Date uploadTime;
	private int clickNum;
	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setFname(String fname){
		this.fname = fname;
	}

	public String getFname(){
		return fname;
	}

	public void setUserId(long userId){
		this.userId = userId;
	}

	public long getUserId(){
		return userId;
	}

	public void setIntro(String intro){
		this.intro = intro;
	}

	public String getIntro(){
		return intro;
	}

	public void setTags(String tags){
		this.tags = tags;
	}

	public String getTags(){
		return tags;
	}

	public void setUploadTime(Date uploadTime){
		this.uploadTime = uploadTime;
	}

	public Date getUploadTime(){
		return uploadTime;
	}

	public void setClickNum(int clickNum){
		this.clickNum = clickNum;
	}

	public int getClickNum(){
		return clickNum;
	}

}

