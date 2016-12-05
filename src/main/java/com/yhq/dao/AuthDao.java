package com.yhq.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.dao.PageDao;
import com.yhq.model.User;

@Repository
public class AuthDao extends PageDao<User> {
	
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public List<User> findByUsername(String username, String type) {
		StringBuilder hql = new StringBuilder(" ");
		hql.append("from User ");
		hql.append("where username = :username ");
		hql.append("and type = :type ");
		Query<User> query = getSession().createQuery(hql.toString(), User.class);
		query.setParameter("username", username);
		query.setParameter("type", type);
		List<User> list = query.getResultList();
		return list;
	}

	public List<User> getUsers() {
		StringBuilder hql = new StringBuilder(" ");
		hql.append("from User ");
		hql.append("where type = :type ");
		Query<User> query = getSession().createQuery(hql.toString(), User.class);
		query.setParameter("type", "1");
		List<User> list = query.getResultList();
		return list;
	}
	
	/**
	 * 添加用户
	 * @param user
	 */
	public void save(User user) {
		getSession().save(user);
	}
}
