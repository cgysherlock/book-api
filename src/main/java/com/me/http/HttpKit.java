package com.me.http;

import com.alibaba.fastjson.JSONObject;
import com.yhq.model.User;

public class HttpKit {

	/**
	 * 将response转化为json字符串
	 * @param response
	 * @return
	 */
	public static String toJson(Response response) {
		String jonStr = JSONObject.toJSON(response).toString();
		return jonStr;
	}

	public static String OK(Response response) {
		return "";
	}

	/**
	 * 获得当前用户信息
	 * @param accessToken
	 * @return
	 */
	public static User getCurrentUser(String accessToken) {
		Token token = (Token) Session.get(accessToken);
		if (token != null) {
			return (User) token.getData();
		}
		return null;
	}
}
