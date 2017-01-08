package com.yhq.model;

import java.io.Serializable;

public class Register implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tel;
	private String password;
	private String smsCode;
	
	public Register() {
		
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	
	
}
