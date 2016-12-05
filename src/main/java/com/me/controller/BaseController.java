package com.me.controller;


import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.me.model.Message;

import redis.clients.jedis.JedisPoolConfig;


@Controller
public class BaseController {
	
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	@Autowired
	private RedisCacheManager redisCacheManager;
	@Autowired
	private JedisPoolConfig jedisPoolConfig;

	public void setMessage(Message message, HttpServletRequest request) {
		request.setAttribute("message", message);
	}

	public void setMsg(Message msg, HttpServletRequest request) {
		request.getSession().setAttribute("msg", msg);
	}

	public String getLoginUrl() {
		return "admin/login";
	}
	
	public String getUri(String action) {
		return getRequestMappingValue() + "/" + action;
	}

	public String redirect(String action) {
		return "redirect:" + getRequestMappingValue() + "/" + action;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}
	
	/**
	 * 设置redis缓存中的值
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setRedis(String key, String value) {
		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] k  = serializer.serialize(key);  
                byte[] name = serializer.serialize(value);  
                return connection.setNX(k, name);
            }  
        });  
        return result;
	}
	
	/**
	 * redis中删除相应key的值
	 * @param key
	 */
	public void removeInRedis(String key) {   
        redisTemplate.delete(key);
    }
	
	/**
	 * 从redis中根据key获得值
	 * @param key
	 * @return
	 */
	public String getRedis(String key) {
		String result = redisTemplate.execute(new RedisCallback<String>() {  
            public String doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();  
                byte[] k = serializer.serialize(key);  
                byte[] value = connection.get(k);
                if (value == null) {  
                    return null;  
                }
                String name = serializer.deserialize(value);
                return name;  
            }  
        });  
        return result;
	}
	
	/**
	 * 根据key值更新redis中数据行
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean updateRedis(String key, String value) {  
        if (this.getRedis(key) == null) {  
            throw new NullPointerException("数据行不存在, key = " + key);  
        }  
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {  
            public Boolean doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
            	RedisSerializer<String> serializer = redisTemplate.getStringSerializer();   
                byte[] k  = serializer.serialize(key);  
                byte[] name = serializer.serialize(value);  
                connection.set(k, name);  
                return true;  
            }  
        });  
        return result;  
    } 

	/**
	 * 获得映射的路径值
	 * @return
	 */
	public String getRequestMappingValue() {
		Class<? extends BaseController> clazz = this.getClass();
		RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
		return requestMapping.value()[0];
	}
	
	/**
	 * 换行打印
	 * @param Str
	 */
	public void println(String str){
		System.out.println(str);
	}
	
	/**
	 * 打印
	 * @param Str
	 */
	public void print(String str){
		System.out.print(str);
	}

	public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	public RedisCacheManager getRedisCacheManager() {
		return redisCacheManager;
	}

}
