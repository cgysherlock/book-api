package com.me.http;

import java.util.Date;
import java.util.UUID;

import com.me.model.Message;

public class Token {
	
	/**
	 * 创建时间
	 */
	private Long createTime;
	/**
	 * 访问令牌
	 */
	private String accessToken;
	
	/**
	 * 生成令牌
	 * @return
	 */
	public static Token generatorToken() {
		Token token = new Token();
		long time = new Date().getTime();
		String accessToken = UUID.randomUUID().toString() + "-" + new Date().getTime();
		token.setCreateTime(time);
		token.setAccessToken(accessToken);
		return token;
	}
	
	/**
	 * 检查令牌
	 * @param token
	 * @return
	 */
	public static Message checkToken(Token token) {
		if (token == null) return Message.warn("token 不存在");
		String accessToken = token.getAccessToken();
		Message message = null;
		if (Session.get(accessToken) != null) {
			message = Message.success("token 验证成功");
		} else {
			message = Message.warn("token 不存在");
		}
		return message;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	
}
