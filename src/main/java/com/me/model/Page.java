package com.me.model;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable{
	

	private List<T> list;	 //保存查询的数据
	private int pageNumber;	 //当前页
	private int pageSize;	 //每页的数据数量	
	private int totalPage;   //总页数
	private int totalRow;    //总记录数
	
	public Page(List<T> list, int pageNumber, int pageSize, int totalPage, int totalRow) {
		super();
		this.list = list;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalPage = totalPage;
		this.totalRow = totalRow;
	}
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}	
	
	
	
	
}
