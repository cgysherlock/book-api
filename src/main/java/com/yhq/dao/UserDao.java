package com.yhq.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.dao.PageDao;
import com.yhq.model.User;

@Repository
@Transactional
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

	/**
	 * 根据id获得用户
	 * @param id
	 * @return
	 */
	public User getById(Long id) {
		return getSession().get(User.class, id);
	}

	/**
	 * 添加关注
	 * @param concerner_id
	 * @param concerned_id
	 * @return
	 */
	public boolean addConcern(Long concerner_id,Long concerned_id) {
		Query<?> query =getSession().createNativeQuery("insert into ssf_concern(create_date,modify_date,concerner_id,concerned_id) values(?,?,?,?)");
		query.setParameter(1, new Date());
		query.setParameter(2, new Date());
		query.setParameter(3, concerner_id);
		query.setParameter(4, concerned_id);
		return query.executeUpdate()>0;
	}
	
	/**
	 * 查询粉丝
	 * @param concernerId
	 * @return
	 */
	public List<User> getConcerneds(Long concernerId) {
		Query<User> query=getSession().createNativeQuery("select * from sys_user user,ssf_concern concern where user.id=concern.concerned_id and concerner_id=?",User.class);
		query.setParameter(1, concernerId);
		return query.getResultList();
	}
	
}
