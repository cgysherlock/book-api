package com.yhq.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.me.dao.BaseDao;
import com.yhq.model.BookShelf;
import com.yhq.model.BookShelfCollection;
import com.yhq.model.BookshelfLike;
import com.yhq.model.BookshelfComment;
import com.yhq.model.Comment;
import com.yhq.model.User;

@Repository
public class ShelfDao extends BaseDao {
	
	
	/**
	 * 获得当前用户的某个书架
	 * @param user
	 * @param id
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<?> getUserOneShelf(User user , Long id) {
		StringBuilder sql = new StringBuilder("");
		sql.append("SELECT ssf_bookshelf.*, ssf_comment.id commentId, ssf_comment.content commentContent, ssf_comment.score commentScore,");
		sql.append("(SELECT count(1) from ssf_bookshelf, ssf_bookshelf_collection where ssf_bookshelf.id = ssf_bookshelf_collection.bookshelf_id) collectionNum");
		sql.append("(SELECT count(1) from ssf_bookshelf, ssf_bookshelf_like where ssf_bookshelf.id = ssf_bookshelf_like.bookshelf_id) likeNum");
		sql.append("FROM ssf_bookshelf");
		sql.append("LEFT JOIN ssf_bookshelf_comment");
		sql.append("ON ssf_bookshelf.id = ssf_bookshelf_comment.bookshelf_id");
		sql.append("LEFT JOIN ssf_comment");
		sql.append("ON ssf_bookshelf_comment.comment_id = ssf_comment.id");
		sql.append("WHERE ssf_bookshelf.id = :bookshhelfId AND ssf_bookshelf.user_id = :userId");
		Query<?> query = getSession().createNativeQuery(sql.toString());
		query.setParameter("bookshhelfId", id);
		query.setParameter("userId", user.getId());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = query.list();
		return list;
	}
	/**
	 * 获取用户的书架
	 * @param user
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<?> getUserShelves(User user) {
		StringBuilder sql = new StringBuilder("");
		sql.append("select ssf_bookshelf.*, ");
		sql.append("(select count(1) from ssf_bookshelf, ssf_bookshelf_collection where ssf_bookshelf.id = ssf_bookshelf_collection.bookshelf_id) collectionNum , ");
		sql.append("(select count(1) from ssf_bookshelf, ssf_bookshelf_comment where ssf_bookshelf.id = ssf_bookshelf_comment.bookshelf_id) commentNumm , ");
		sql.append("(select count(1) from ssf_bookshelf, ssf_bookshelf_like where ssf_bookshelf.id = ssf_bookshelf_like.bookshelf_id) likeNum");
		sql.append("from ssf_bookshelf  ");
		sql.append("where ssf_bookshelf.user_id = :id");
		Query<?> query = getSession().createNativeQuery(sql.toString());
		query.setParameter("id", user.getId());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<?> list = query.list();
		return list;
	}
	public List<Comment> getShelfComments(Long id) {
		Query<Comment> query = getSession().createNativeQuery("select * from ssf_bookshelf bookshelf ,ssf_bookshelf_comment shelfcomment,ssf_comment comment where bookshelf.id=shelfcomment.bookshelf_id and shelfcomment.comment_id=comment.id and bookshelf.id=? ",Comment.class);
		query.setParameter(1, id);
		return query.getResultList();
	}
	
	public boolean saveComment(Comment comment) {
		comment.setCreateDate(new Date());
		comment.setModifyDate(new Date());
		return (Long)getSession().save(comment) != null;
	}
	
	public boolean commentShelf(Long id,Long commentid) {
		BookshelfComment bookshelfComment = new BookshelfComment();
		bookshelfComment.setCreateDate(new Date());
		bookshelfComment.setModifyDate(bookshelfComment.getCreateDate());
		bookshelfComment.setCommentId(commentid);
		bookshelfComment.setBookshelfId(id);
		Long shelfId = (Long)getSession().save(bookshelfComment);
		return shelfId != null;
	}
	
	public List<BookShelf> getCurrentUserShelves(Long id){
		String sql="select * from ssf_bookshelf bookshelf where bookshelf.id in(select bookshelf_id from ssf_bookshelf_collection collection where collection.user_id=?)";
		Query<BookShelf> query=getSession().createNativeQuery(sql,BookShelf.class);
		query.setParameter(1, id);
		return query.getResultList();
	}
	
	public boolean saveShelf(BookShelfCollection bookShelfCollection) {
		  Long bookshelfcollectionid=(Long)getSession().save(bookShelfCollection);
		  return bookshelfcollectionid>0;
	}
	
	public boolean updateCurrentUserShelf(Long id,BookShelf bookShelf, Long userid) {
		String sql="update ssf_bookshelf set modify_date =? ,name=? where ssf_bookshelf.id=? and ssf_bookshelf.user_id=?";
		Query<?> query=getSession().createNativeQuery(sql);
		query.setParameter(1, new Date());
		query.setParameter(2, bookShelf.getName());
		query.setParameter(3, id);
		query.setParameter(4, userid);
		return query.executeUpdate()>0;
	}

	public boolean createBookShelf(BookShelf bookShelf) {
		Long id=(Long)getSession().save(bookShelf);
		return id>0;
	}

	/**
	 * 设置喜欢某个书架
	 * @param user
	 * @param bookShelf
	 * @return
	 */
	public boolean likeBookshelf(User user , BookShelf bookShelf) {
		try{
			BookshelfLike bookShelfLike = new BookshelfLike();
			bookShelfLike.setUserId(user.getId());
			bookShelfLike.setBookshelfId(bookShelf.getId());
			getSession().save(bookShelfLike);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public boolean dislikeBookShelf(Long id, Long id2) {
		Query<?> query=getSession().createNativeQuery("delete from ssf_bookshelf_like where ssf_bookshelf_like.bookshelf_id=? and ssf_bookshelf_like.user_id=? ");
		query.setParameter(1, id);
		query.setParameter(2, id2);
		return query.executeUpdate()>0;
	}


	public List<BookShelf> test() {
		Query<BookShelf> query=getSession().createNativeQuery("select bookshelf.id,bookshelf.create_date,bookshelf.modify_date,bookshelf.name,bookshelf.user_id,user.name userName from ssf_bookshelf bookshelf left join sys_user user on bookshelf.user_id=user.id",BookShelf.class);
		return query.getResultList();
	}

}
