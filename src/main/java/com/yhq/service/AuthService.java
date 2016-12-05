package com.yhq.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhq.dao.EquipmentDao;
import com.yhq.dao.UserDao;
import com.me.http.Session;
import com.me.http.Token;
import com.me.model.Message;
import com.me.plugin.Redis;
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

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public Message doRegist(User user) {
		Message message = null;
		List<User> list = userDao.findByTel(user.getTel());
		User u = list.size() > 0 ? list.get(0) : null;
		if (u == null){
			userDao.save(user);
			message = Message.success("注册成功");
			message.dataPut("access_token", getAccessToken(user));
		} else {
			message = Message.error("用户名已经存在");
		}
		return message;
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}

	/**
	 * 登录验证
	 * @param tel
	 * @param password
	 * @return
	 */
	public Message verification(String tel, String password) {
		Message message = null;
		List<User> list = userDao.findByTel(tel);
		User user = list.size() > 0 ? list.get(0) : null;
		if (user != null){
			if (checkPassword(user, password)) {
				message = Message.success("登录成功");
				message.dataPut("access_token", getAccessToken(user));
			} else {
				message = Message.error("密码错误");
			}
		} else {
			message = Message.error("用户名不存在");
		}
		return message;
	}
	
	/**
	 * 获得访问令牌
	 * @param user
	 * @return
	 */
	public String getAccessToken(User user) {
		Token token = Token.generatorToken();
		String accessToken = token.getAccessToken();
		Session.put(accessToken, user);
		return accessToken;
	}
}
