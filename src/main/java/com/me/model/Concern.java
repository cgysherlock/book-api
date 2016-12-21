package com.me.model;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ssf_concern")
public class Concern {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="modify_date")
	private Date modifyDate;
	@Column(name="concerner_id")
	private long concernerid;
	@Column(name="concerned_id")
	private long concernedid;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="concerner_id",updatable=false)
	private User concerner;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="concerned_id",updatable=false)
	private User concerned;
	
	
	public User getConcerner() {
		return concerner;
	}
	public void setConcerner(User concerner) {
		this.concerner = concerner;
	}
	public User getConcerned() {
		return concerned;
	}
	public void setConcerned(User concerned) {
		this.concerned = concerned;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public long getConcernerid() {
		return concernerid;
	}
	public void setConcernerid(long concernerid) {
		this.concernerid = concernerid;
	}
	public long getConcernedid() {
		return concernedid;
	}
	public void setConcernedid(long concernedid) {
		this.concernedid = concernedid;
	}
}
