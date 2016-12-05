package com.me.model;

import java.util.HashMap;
import java.util.Map;

public class Message {

	private String type;
	
	private String content;
	
	private Map<String, Object> data = new HashMap<String, Object>();
	

	public Message(){
		
	}
	
	/**
	 * 
	 * @param type
	 * @param content
	 */
	public Message(String type, String content){
		this.type = type;
		this.content = content;
	}
	
	
	/**
	 * 
	 * @param content
	 * @return
	 */
	public static Message success(String content){
		return new Message("success", content);
	}
	
	
	/**
	 * 
	 * @param content
	 * @return
	 */
	public static Message error(String content){
		return new Message("error", content);
	}
	
	
	/**
	 * 
	 * @param content
	 * @return
	 */
	public static Message warn(String content){
		return new Message("warn", content);
	}
	
	public void dataPut(String key, Object value) {
		data.put(key, value);
	}
	
	public Object dataGet(String key) {
		return data.get(key);
	}
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	

}
