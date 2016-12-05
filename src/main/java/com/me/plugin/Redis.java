package com.me.plugin;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.yhq.dao.AuthDao;

import redis.clients.jedis.JedisPoolConfig;

public class Redis {
	/**
	 * 设置redis中的String类型的值
	 * @param key
	 * @param value
	 */
	public static void set(String key, String value) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder.getBean("redisTemplate");
		ValueOperations<Serializable, Serializable> valueOperations = redisTemplate.opsForValue();
		valueOperations.set(key, value);
	}
	
	/**
	 * 获得redis中的String类型的值
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		RedisTemplate<Serializable, Serializable> redisTemplate = SpringContextHolder.getBean("redisTemplate");
		ValueOperations<Serializable, Serializable> valueOperations = redisTemplate.opsForValue();
		return (String) valueOperations.get(key);
	}
	
}
