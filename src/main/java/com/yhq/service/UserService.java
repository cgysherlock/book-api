package com.yhq.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yhq.dao.UserDao;
import com.me.model.Message;
import com.yhq.model.User;

@Service
@Transactional
public class UserService {
	
	@Autowired
	UserDao userDao;
	
 
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public Message delete(Long id) {
		User user = new User();
		user.setId(id);
		return userDao.delete(user) ? Message.success("删除成功") : Message.error("删除失败");
	}

	/**
	 * 根据id获得用户
	 * @param id
	 * @return
	 */
	public Message getById(Long id) {
		Message message = null;
		User user = userDao.getById(id);
		message =  user!= null ? Message.success("查询成功") : Message.error("查询失败");
		message.dataPut("model", user);
		return message;
	}
}
