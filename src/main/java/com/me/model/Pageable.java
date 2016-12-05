package com.me.model;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import com.me.kit.ParaKit;



public class Pageable {
	private final static int DEFAULT_PAGENUMBER = 1;
	private final static int DEFAULT_PAGESIZE = 20;
	private int pageNumber = 1;   //当前页数
	private int pageSize = 2;	  //每页的记录数
	private int preRow;       //前几页的记录数
	private String searchProperty;  //要查询的参数
	private String searchValue;		//要查询的值
	private String orderProperty;   //排序的参数
	private String orderDirection;  //排序的方向
	public Pageable(){
		
	}
	public Pageable(HttpServletRequest request){
		setValue(request);
		setMsgFlag(request);
	}
	private void setMsgFlag(HttpServletRequest request) {
		HttpSession session = request.getSession();
		// TODO Auto-generated method stub
		if(session.getAttribute("msg")!=null){
			request.setAttribute("msg", request.getAttribute("msg"));
			session.removeAttribute("msg");
		}
	}
	public Pageable(int pageNumber, int pageSize){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
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
	
	public int getPreRow() {
		return preRow;
	}
	public void setPreRow(int preRow) {
		this.preRow = preRow;
	}
	public String getSearchProperty() {
		return searchProperty;
	}
	public void setSearchProperty(String searchProperty) {
		this.searchProperty = searchProperty;
	}
	public String getSearchValue() {
		return searchValue;
	}
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	public String getOrderProperty() {
		return orderProperty;
	}
	public void setOrderProperty(String orderProperty) {
		this.orderProperty = orderProperty;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	public void setValue(HttpServletRequest request){
		if(ParaKit.notBlank(request.getParameter("pageNumber"))){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}else{
			pageNumber = DEFAULT_PAGENUMBER;
		}
		if(ParaKit.notBlank(request.getParameter("pageSize"))){
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}else{
			pageSize = DEFAULT_PAGESIZE;
		}
		if(ParaKit.notBlank(request.getParameter("searchProperty"))){
			searchProperty = request.getParameter("searchProperty");
		}
		if(request.getParameter("searchValue")!=null){
			searchValue = request.getParameter("searchValue");
		}
		if(ParaKit.notBlank(request.getParameter("orderProperty"))){
			orderProperty = request.getParameter("orderProperty");
		}
		if(ParaKit.notBlank(request.getParameter("orderDirection"))){
			orderDirection = request.getParameter("orderDirection");
		}
	}

}
