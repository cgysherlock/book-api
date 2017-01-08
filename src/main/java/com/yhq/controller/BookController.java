package com.yhq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.service.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookController extends BaseController{
	@Autowired
	private BookService bookService;
	
	/**
	 * 获取所有书本
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAllBook() {
		Message message = bookService.getAllBook();
		Response response =  new Response(message);
		return HttpKit.toJson(response);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String add(
			 String type
			) {
		Message message = bookService.getBookByType(type);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String delete(@PathVariable Long id) {
		Message message = bookService.deleteBook(id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 点赞某条对书的评论
	 * @param commentid
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value="/comments/{comment_id}/like",method = RequestMethod.PATCH, produces = "application/json;charset=UTF-8")
	public String likeCommentOfBook(@PathVariable(name="comment_id") Long commentid,@RequestHeader String authorization){
		Message message= bookService.likeCommentOfBook(
//				HttpKit.getCurrentUser(authorization).getId()
				Long.valueOf(1)
				, commentid);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 取消点赞某条对书的评论
	 * @param commentid
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value="/comments/{comment_id}/unlike",method = RequestMethod.PATCH, produces = "application/json;charset=UTF-8")
	public String unlikeCommentOfBook(@PathVariable(name="comment_id") Long commentid,@RequestHeader String authorization){
		Message message= bookService.unlikeCommentOfBook(
//				HttpKit.getCurrentUser(authorization).getId()
				Long.valueOf(1)
				, commentid);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
}
