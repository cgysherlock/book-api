package com.yhq.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.model.Message;
import com.yhq.dao.ShelfDao;
import com.yhq.model.BookShelf;
import com.yhq.model.BookShelfCollection;
import com.yhq.model.Comment;

@Service
@Transactional
public class ShelfService {

	@Autowired ShelfDao shelfDao;
	
	public Message getShelfComments(Long id){
		Message message;
		List<Comment> comments=shelfDao.getShelfComments(id);
		if (comments.isEmpty()) {
			message=Message.warn("还没有评论");
		}else {
			message=Message.success("查询评论成功");
			message.dataPut("comments",comments);
		}
		return message;
	}
	
	public Message commentShelf(Long id,Comment comment) {
		shelfDao.saveComment(comment);
		System.out.println("ididid"+comment.getId());
		boolean issuccess=shelfDao.commentShelf(id, comment.getId());
		return issuccess?Message.success("评论成功"):Message.error("评论失败");
	}
	
	public Message getCurrentUserShelves(Long id) {
		Message message;
		List<BookShelf> list=shelfDao.getCurrentUserShelves(id);
		if (list.isEmpty()) {
			message=Message.warn("还没有书架");
		}else {
			message=Message.success("查询书架成功");
			message.dataPut("bookshelves", list);
		}
		return message;
	}
	
	public Message saveShelf(Long id,BookShelfCollection bookShelfCollection,Long userId) {
		bookShelfCollection.setBookshelfId(id);
		bookShelfCollection.setUserId(userId);
		bookShelfCollection.setCreateDate(new Date());
		bookShelfCollection.setModifyDate(new Date());
		return shelfDao.saveShelf(bookShelfCollection)?Message.success("收藏成功"):Message.error("收藏失败");
	}
	
	public Message updateCurrentUserShelf(Long id,BookShelf bookShelf, Long userid) {
		return shelfDao.updateCurrentUserShelf(id,bookShelf,userid)?Message.success("更新成功"):Message.error("更新出错");
	}

	public Message createBookShelf(BookShelf bookShelf, Long userid) {
		bookShelf.setCreateDate(new Date());
		bookShelf.setModifyDate(new Date());
		bookShelf.setUserId(userid);
		return shelfDao.createBookShelf(bookShelf)?Message.success("创建成功"):Message.error("创建失败");
	}

	public Message dislikeBookShelf(Long id, Long id2) {
		return shelfDao.dislikeBookShelf(id,id2)?Message.success("取消喜欢书架成功"):Message.error("取消喜欢书架失败");
	}
}
