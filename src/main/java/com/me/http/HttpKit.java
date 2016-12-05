package com.me.http;

import com.alibaba.fastjson.JSONObject;

public class HttpKit {
	
	public static String toJson(Response response) {
		String jonStr = JSONObject.toJSON(response).toString();
		return jonStr;
	}
	
	public static String OK(Response response) {
		return "";
	}
}
