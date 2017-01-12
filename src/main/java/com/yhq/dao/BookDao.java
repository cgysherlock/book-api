package com.yhq.dao;


import java.util.List;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.me.dao.PageDao;
import com.yhq.model.Book;
import com.yhq.model.BookCollection;
import com.yhq.model.BookComment;
import com.yhq.model.BookLike;
import com.yhq.model.Comment;
import com.yhq.model.User;

@Repository
@Transactional
public class BookDao extends PageDao<Book>{
	
	
	/**
	 * 设置喜欢某本书
	 * @param user
	 * @param book
	 * @return
	 */
	public boolean likeBook(User user, Book book) {
		try{
			BookLike bookLike = new BookLike();
			bookLike.setBookId(book.getId());
			bookLike.setUserId(user.getId());
			getSession().save(bookLike);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/**
	 * 设置不喜欢某本书
	 * @param user
	 * @param bookId
	 * @return
	 */
	public boolean unlike(User user, Long bookId) {
		try{
			StringBuilder sql = new StringBuilder("");
			sql.append("delete ssf_book_like where ssf_book_like.user_id = :userId and ssf_book_like.book_id = :bookId");
			Query<?> query = getSession().createNativeQuery(sql.toString());
			query.setParameter("userId", user.getId());
			query.setParameter("bookId", bookId);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean collectionBook(User user, Book book) {
		try{
			BookCollection bookCollection = new BookCollection();
			bookCollection.setBookId(book.getId());
			bookCollection.setUserId(user.getId());
			getSession().save(bookCollection);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/**
	 * 对某书进行评论
	 * @param user
	 * @param book
	 * @param comment
	 * @return
	 */
	public boolean commentBook(User user, Book book, Comment comment) {
			try{
				getSession().save(comment);
				BookComment bookComment = new BookComment();
				bookComment.setBookId(book.getId());
				bookComment.setCommentId(comment.getId());
				getSession().save(bookComment);
				return true;
			}
			catch (Exception e) {
				// TODO: handle exception
				return false;
			}
	}
	/**
	 * 查询当前用户收藏的书
	 * @param user
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<?> queryCollectionBook(User user) {
		StringBuilder sql = new StringBuilder("");
		sql.append("SELECT book.* ");
		sql.append("FROM ssf_book book , ssf_book_collection collection ");
		sql.append("WHERE book.id = collection.book_id and collection.user_id = :id");
		NativeQuery<?> query = getSession().createNativeQuery(sql.toString());
		query.setParameter("id", user.getId());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = query.list();
		return list;
	}
	
	 /**
	  * 获取某本书的评论
	  * @param book
	  * @return
	  */
	@SuppressWarnings("deprecation")
	public List<?> getOneBookComment(Book book) {
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT comment.id , comment.content , user.id userId , user.password , user.name commenterName , user.photo , user.tel, user.email");
		sql.append(" FROM ssf_comment comment ");
		sql.append("left JOIN ssf_book_comment book_comment on comment.id = book_comment.comment_id ");
		sql.append("left join ssf_user_comment user_comment on comment.id = user_comment.comment_id ");
		sql.append("left join sys_user user on user_comment.user_id = user.id ");
		sql.append("where book_comment.book_id = :id ");
		NativeQuery<?> query = getSession().createNativeQuery(sql.toString());
		query.setParameter("id", book.getId());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = query.list();
		return list;
	}
	/**
	 * 添加某本书
	 * @param book
	 */
	public boolean addBook(Book book) {
		try{
			System.out.println(book.getName());
			getSession().save(book);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
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
	
	
	/**
	 * 查询某本书
	 * （包括评论数）
	 * @param book
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<?> queryOneBook(Book book) {
		StringBuilder sql = new StringBuilder("");
		sql.append("select ssf_book.*, ( select COUNT(1) from ssf_book, ssf_book_comment where ssf_book.id = ssf_book_comment.book_id and ssf_book.id = :id ) count  from ssf_book ");
		NativeQuery<?> query = getSession().createNativeQuery(sql.toString());
		query.setParameter("id", book.getId());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = query.list();
		return list;
	}
	/**
	 * 查询所有书本
	 *（包括有评论的数量）
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<?> queryAllBook() {
		StringBuilder sql = new StringBuilder("");
		sql.append("select ssf_book.*, ( select COUNT(1) from ssf_book, ssf_book_comment where ssf_book.id = ssf_book_comment.book_id ) count  from ssf_book ");
		NativeQuery<?> sqlQuery = getSession().createNativeQuery(sql.toString());
		sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = sqlQuery.list();
		return list;
	}
	
	/**
	 * 删除书本
	 * @param id
	 */
	public boolean deleteBook(Book book) {
		StringBuilder hql = new StringBuilder("");
		hql.append("delete ");
		hql.append("from Book ");
		hql.append("where id = :id");
		Query<?> query = getSession().createQuery(hql.toString());
		query.setParameter("id", book.getId());
		int count = query.executeUpdate();
		return count > 0;
	}
}
