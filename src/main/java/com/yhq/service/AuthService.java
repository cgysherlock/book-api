package com.yhq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhq.dao.UserDao;
import com.me.http.Session;
import com.me.http.Token;
import com.me.kit.StrKit;
import com.me.model.Message;
import com.yhq.model.User;

@Service
public class AuthService {
	
	@Autowired
	UserDao userDao;
	
	/**
	 * 验证密码
	 * @param user
	 * @param password
	 * @return
	 */
	public boolean checkPassword(User user, String password) {
		return user.getPassword().equals(password) ? true : false;
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	/**
	 * 权限验证
	 * @param tel
	 * @param password
	 * @return
	 */
	public Message verification(String tel, String password, String SMSCode) {
		
		if (StrKit.isBlank(tel)) {
			return Message.error("手机不能为空");
		}
		
		/**
		 * 密码登陆
		 */
		if (StrKit.notBlank(password) && StrKit.isBlank(SMSCode)) {
			return verificationByPassword(tel, password);
		}
		
		/**
		 * 验证码登陆
		 */
		if (StrKit.isBlank(password) && StrKit.notBlank(SMSCode)) {
			return verificationBySmsCode(tel, SMSCode);
		}
		
		/**
		 * 注册
		 */
		if (StrKit.notBlank(password) && StrKit.notBlank(SMSCode)) {
			return doRegist(tel, password, SMSCode);
		}
		
		return Message.error("未知错误");
	}
	
	/**
	 * 密码登陆
	 * @param tel
	 * @param password
	 * @return
	 */
	public Message verificationByPassword(String tel, String password) {
		Message message = null;
		List<User> list = userDao.findByTel(tel);
		User user = list.size() > 0 ? list.get(0) : null;
		if (user != null){
			if (checkPassword(user, password)) {
				message = Message.success("登录成功");
				message.dataPut("access_token", getAccessToken(user));
				message.dataPut("model", user);
			} else {
				message = Message.error("密码错误");
			}
		} else {
			message = Message.error("用户名不存在");
		}
		return message;
	}
	
	/**
	 * 验证码登陆
	 * @param tel
	 * @param SMSCode
	 * @return
	 */
	public Message verificationBySmsCode(String tel, String SMSCode) {
		Message message = checkSMSCode(tel, SMSCode);
		if (message.getType() != "success") {
			return message;
		}
		List<User> list = userDao.findByTel(tel);
		User user = list.size() > 0 ? list.get(0) : null;
		if (user != null){
			message = Message.success("登录成功");
			message.dataPut("access_token", getAccessToken(user));
		} else {
			message = Message.error("用户名不存在");
		}
		return message;
	}
	
	/**
	 * 注册用户
	 * @param tel
	 * @param password
	 * @param SMSCode
	 * @return
	 */
	public Message doRegist(String tel, String password, String SMSCode) {
		Message message = checkSMSCode(tel, SMSCode);
		if (message.getType() != "success") {
			return message;
		}
		List<User> list = userDao.findByTel(tel);
		User u = list.size() > 0 ? list.get(0) : null;
		if (u == null){
			User user = new User();
			user.setTel(tel);
			user.setPassword(password);
			userDao.save(user);
			message = Message.success("注册成功");
			message.dataPut("access_token", getAccessToken(user));
		} else {
			message = Message.error("用户名已经存在");
		}
		return message;
	}
	
	/**
	 * 验证验证码
	 * @param tel
	 * @param SMSCode
	 * @return
	 */
	public Message checkSMSCode(String tel, String SMSCode) {
		if (Session.get(tel) != null) {
			return Session.get(tel).toString().equals(SMSCode) 
					? Message.success("验证码验证成功") : Message.warn("验证码错误");
		} else {
			return Message.error("验证码不存在");
		}
	}
	
	
	
	/**
	 * 获得访问令牌
	 * @param user
	 * @return
	 */
	public String getAccessToken(User user) {
		Token token = Token.generatorToken();
		String accessToken = token.getAccessToken();
		token.setData(user);
		Session.put(accessToken, token);
		return accessToken;
	}

	public Message login(String tel, String smsCode) {
		// TODO Auto-generated method stub
		return null;
	}
}
