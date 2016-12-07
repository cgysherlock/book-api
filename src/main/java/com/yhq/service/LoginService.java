package com.yhq.service;

import java.util.List;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhq.dao.UserDao;
import com.me.model.Message;
import com.yhq.model.User;

@Service
public class LoginService {
	
	@Autowired
	UserDao userDao;
	
	/**
	 * 登录验证
	 * @param username
	 * @param password
	 * @return
	 */
	public Message doLogin(String username, String password, String type, HttpSession session) {
		Message message = null;
		List<User> list = userDao.findByUsername(username);
		User user = list.size() > 0 ? list.get(0) : null;
		if (user != null){
			message = checkPassword(user, password) ? Message.success("登录成功") : Message.error("密码错误");
			session.setAttribute("user", user);
		} else {
			message = Message.error("用户名不存在");
		}
		return message;
	}
	
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
	public Message doRegist(User user, HttpSession session) {
		Message message = null;
		byte type = 1;
		List<User> list = userDao.findByUsername(user.getUsername());
		User u = list.size() > 0 ? list.get(0) : null;
		if (u == null){
			userDao.save(user);
			message = Message.success("注册成功");
		} else {
			message = Message.error("用户名已经存在");
		}
		return message;
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}
}
