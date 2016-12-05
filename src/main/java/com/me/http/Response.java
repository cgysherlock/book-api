package com.me.http;

import java.util.Date;
import java.util.Map;

import com.me.model.Message;

public class Response {
	private Date responseTime;
	private Message message;
	private Object data;

	public Response() {

	}

	public Response(Message message) {
		this.message = message;
	}

	public Response(Object data) {
		this.data = data;
	}

	public Response(Object data, Message message) {
		this.data = data;
		this.message = message;
	}

	public Date getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
	
	/**
	 * 返回状态为成功的并带返回数据的结果
	 * @param content
	 * @param data
	 * @return
	 */
	public static String success(String content, Map<String, Object> data) {
		Response response = new Response();
		Message message = Message.success(content);
		message.setData(data);
		response.setMessage(message);
		response.setResponseTime(new Date());
		return HttpKit.toJson(response);
	}
	
	/**
	 * 返回成功不带数据的结果
	 * @param content
	 * @return
	 */
	public static String success(String content) {
		Response response = new Response();
		Message message = Message.success(content);
		response.setMessage(message);
		response.setResponseTime(new Date());
		return HttpKit.toJson(response);
	}

	/**
	 * 返回错误带数据的结果
	 * @param content
	 * @param data
	 * @return
	 */
	public static String error(String content, Map<String, Object> data) {
		Response response = new Response();
		Message message = Message.error(content);
		message.setData(data);
		response.setMessage(message);
		response.setResponseTime(new Date());
		return HttpKit.toJson(response);
	}
	
	/**
	 * 返回错误，不带数据的结果
	 * @param content
	 * @return
	 */
	public static String error(String content) {
		Response response = new Response();
		Message message = Message.error(content);
		response.setMessage(message);
		response.setResponseTime(new Date());
		return HttpKit.toJson(response);
	}
}
