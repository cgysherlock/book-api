package com.me.http;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Session{
	
	/**
	 * session缓存
	 */
	public static Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 过期时间
	 */
	public static Map<String, Long> timeout = new HashMap<String, Long>();
	/**
	 * 创建时间
	 */
	public static Map<String, Long> createTime = new HashMap<String, Long>();
	/**
	 * 默认过期时间（ms）
	 */
	public static final long DEFAULT_TIMEOUT = 86400000;
	/**
	 * 开启一个线程查看缓存是否过期
	 */
	static Thread liveThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true) {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (String key : map.keySet()) {
					long thisTimeout = timeout.get(key);
					long thisCreateTime = createTime.get(key);
					long currentTime = new Date().getTime();
					if (currentTime - thisCreateTime > thisTimeout) {
						Session.remove(key);
					}
				}
			}
		}
		
	});
	
	static {
		liveThread.start();
	}

	
	/**
	 * 设置session的值
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, Object> put(String key, Object value) {
		map.put(key, value);
		timeout.put(key, DEFAULT_TIMEOUT);
		createTime.put(key, new Date().getTime());
		return map;
	}
	
	/**
	 * 获得session的值
	 * @param key
	 * @return
	 */
	public static Object get(String key) {
		return map.get(key);
	}
	
	/**
	 * 移除session的值
	 * @param key
	 * @return
	 */
	public static void remove(String key) {
		if (timeout.containsKey(key)) timeout.remove(key);
		if (createTime.containsKey(key)) createTime.remove(key);
		if (map.containsKey(key)) map.remove(key);
	}
	
	/**
	 * 设置过期时间
	 * @param key
	 * @param time
	 */
	public static void setTimeout(String key, Long time) {
		timeout.put(key, time);
	}
	
	/**
	 * 获得过期时间
	 * @param key
	 * @return
	 */
	public static Long getTimeout(String key) {
		return timeout.get(key);
	}
	
	/**
	 * 清除过期时间
	 * @param key
	 * @return
	 */
	public static boolean clearTimeout(String key) {
		if (timeout.containsKey(key)) {
			return timeout.remove(key) > 0;
		} else {
			return true;
		}
	}
}
