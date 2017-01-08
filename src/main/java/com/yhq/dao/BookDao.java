package com.yhq.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.dao.PageDao;
import com.yhq.model.Book;

@Repository
public class BookDao extends PageDao<Book>{
	
	/**
	 * 查找所有的书本
	 * @return
	 */
	public List<Book> findAllBook() {
		StringBuilder hql = new StringBuilder("");
		hql.append("from Book");
		Query<Book> query = getSession().createQuery(hql.toString(), Book.class);
		List<Book> list = query.getResultList();
		return list;
	}
	
	/**
	 * 查找部分书本
	 * 根据类别
	 * @param type
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Book> findBookByType(String type) {
		if (type.equals("") || type == null) {
			return findAllBook();
		}
		StringBuilder hql = new StringBuilder("");
		hql.append("from Book");
		hql.append(" where type = ?");
		Query query = getSession().createQuery(hql.toString());
		int typeInt = Integer.parseInt(type);
		query.setParameter(0, typeInt);
		List<Book> list = query.getResultList();
		return list;
	}
	
	/**
	 * 删除书本
	 * @param id
	 */
	public boolean deleteBook(Long id) {
		if (id == null) {
			return false;
		}
		Book book = new Book();
		book.setId(id);
		try{
			getSession().delete(book);
			getSession().flush();
		}
		catch(RuntimeException e) {
			return false;
		}
		return true;
	}

	public boolean likeCommentOfBook(Long userid, Long commentid) {
		Query<?> query=getSession().createNativeQuery(" update ssf_user_comment set ssf_user_comment.like=1 where ssf_user_comment.user_id=? and ssf_user_comment.comment_id=? ");
		query.setParameter(1, userid);
		query.setParameter(2, commentid);
		return query.executeUpdate()>0;
	}

	public boolean unlikeCommentOfBook(Long userid, Long commentid) {
		// TODO Auto-generated method stub
		Query<?> query=getSession().createNativeQuery(" update ssf_user_comment set ssf_user_comment.like=0 where ssf_user_comment.user_id=? and ssf_user_comment.comment_id=? ");
		query.setParameter(1, userid);
		query.setParameter(2, commentid);
		return query.executeUpdate()>0;
	}
	
	
}
