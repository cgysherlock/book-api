package com.me.model;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
	private Map<Integer, String> map = new HashMap<Integer, String>();
	private String type;
	private String name;

	public Map<Integer, String> getMap() {
		return map;
	}

	public void setMap(Map<Integer, String> map) {
		this.map = map;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String get(Integer index){
		return map.get(index);
	}
	
	public void put(Integer index, String value){
		map.put(index, value);
	}
}
