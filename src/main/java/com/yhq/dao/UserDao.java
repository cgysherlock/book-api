package com.yhq.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.dao.PageDao;
import com.yhq.model.User;

@Repository
public class UserDao extends PageDao<User> {
	
	/**
	 * 根据用户名查询用户
	 * @param username
	 * @return
	 */
	public List<User> findByUsername(String username) {
		StringBuilder hql = new StringBuilder(" ");
		hql.append("from User ");
		hql.append("where username = :username ");
		Query<User> query = getSession().createQuery(hql.toString(), User.class);
		query.setParameter("username", username);
		List<User> list = query.getResultList();
		return list;
	}
	
	/**
	 * 根据手机号码查询用户
	 * @param tel
	 * @return
	 */
	public List<User> findByTel(String tel) {
		StringBuilder hql = new StringBuilder(" ");
		hql.append("from User ");
		hql.append("where tel = :tel ");
		Query<User> query = getSession().createQuery(hql.toString(), User.class);
		query.setParameter("tel", tel);
		List<User> list = query.getResultList();
		return list;
	}

	public List<User> getUsers() {
		StringBuilder hql = new StringBuilder(" ");
		hql.append("from User ");
		Query<User> query = getSession().createQuery(hql.toString(), User.class);
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
	
	/**
	 * 删除用户
	 * @param user
	 */
	public boolean delete(User user) {
		StringBuilder hql = new StringBuilder(" delete ");
		hql.append("from User ");
		hql.append("where id = :id");
		Query<?> query = getSession().createQuery(hql.toString());
		query.setParameter("id", user.getId());
		int count = query.executeUpdate();
		return count > 0;
	}
	
}
