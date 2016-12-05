package com.me.model;

import com.me.annotation.TableBind;

public class DbModel {
	
	private String tableName;
	
	public DbModel() {
		TableBind tableBind = this.getClass().getAnnotation(TableBind.class);
		if(tableBind != null){
			tableName = tableBind.tableName();
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	
}
