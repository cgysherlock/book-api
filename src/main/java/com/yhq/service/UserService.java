package com.yhq.service;

import java.util.List;
import java.util.Map;

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

	/**
	 * 添加关注
	 *@param concerner_id 
	 *@param concerned_id
	 */
	public Message addConcern(Long concerner_id,Long concerned_id) {
		Message message=null;
		boolean result= userDao.addConcern(concerner_id, concerned_id);
		message = result?Message.success("关注成功") :Message.error("关注失败");
		return message; 
	}
	
	/**
	 * 查询粉丝
	 * @param concernedId
	 * @return
	 */
	public Message getConcerneds(Long concernedId){
		Message message;
		List<User> users=userDao.getConcerneds(concernedId);
		message = users.isEmpty()?Message.warn("没有人关注你"):Message.success("查询成功");
		if (!users.isEmpty()) {
			message.dataPut("users", users);
		}
		return message;
	}
	
	/**
	 * 取消关注
	 * @param concernerId
	 * @param concernedId
	 * @return
	 */
	public Message deleteConcern(Long concernerId ,Long concernedId) {
		Message message=userDao.deleteConcern(concernerId, concernedId)?Message.success("删除成功"):Message.error("删除失败");
		return message;
	}
	
	/**
	 * 得到粉丝数最多的三个人
	 * @return
	 */
	public Message getFamousUser(){
		Message message;
		List<Map<String, String>> users=userDao.getFamousUser();
		if (users.isEmpty()) {
			message=Message.warn("没有用户。。。。");
		}else {
			message=Message.success("查询粉丝量最多的用户成功");
			message.dataPut("famousUsers", users);
		}
		return message;
	}
	
}
