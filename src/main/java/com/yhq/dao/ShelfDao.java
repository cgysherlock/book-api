package com.yhq.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.dao.BaseDao;
import com.yhq.model.BookShelf;
import com.yhq.model.BookShelfCollection;
import com.yhq.model.BookshelfComment;
import com.yhq.model.Comment;

@Repository
public class ShelfDao extends BaseDao {
	
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

	public boolean dislikeBookShelf(Long id, Long id2) {
		Query<?> query=getSession().createNativeQuery("delete from ssf_bookshelf_like where ssf_bookshelf_like.bookshelf_id=? and ssf_bookshelf_like.user_id=? ");
		query.setParameter(1, id);
		query.setParameter(2, id2);
		return query.executeUpdate()>0;
	}
}
