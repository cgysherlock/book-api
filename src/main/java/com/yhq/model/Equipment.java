package com.yhq.model;

import java.util.Date;


import com.me.annotation.TableBind;
import com.me.model.DbModel;

   /**
    * equipment 实体类
    * Mon Nov 14 23:08:13 CST 2016 yhq
    */ 

@TableBind(tableName="equipment")
public class Equipment extends DbModel{
	private long id;
	private String name;
	private String description;
	private String code;
	private Date addTime;
	private float price;
	private String place;
	private long userId;
	private String userName;
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

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setAddTime(Date addTime){
		this.addTime = addTime;
	}

	public Date getAddTime(){
		return addTime;
	}

	public void setPrice(float price){
		this.price = price;
	}

	public float getPrice(){
		return price;
	}

	public void setPlace(String place){
		this.place = place;
	}

	public String getPlace(){
		return place;
	}

	public void setUserId(long userId){
		this.userId = userId;
	}

	public long getUserId(){
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}

