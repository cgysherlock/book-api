package com.me.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * user 实体类
 * Sat Dec 10 20:22:20 CST 2016 yhq
 */ 
@Entity
@Table(name = "sys_user")
public class User{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "modify_date")
	private Date modifyDate;

	@Column
	private String username;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private String photo;

	@Column
	private String tel;

	@Column
	private String email;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="concerner")
	private Set<Concern> concerners=new HashSet<Concern>(0);
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="concerned")
	private Set<Concern> cocnerneds=new HashSet<Concern>(0);
	
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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setPhoto(String photo){
		this.photo = photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setTel(String tel){
		this.tel = tel;
	}

	public String getTel(){
		return tel;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

}

