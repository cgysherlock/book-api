package com.yhq.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.model.Book;
import com.yhq.service.BookService;

@RestController
@RequestMapping(value = "/books")
public class BookController extends BaseController{
	@Autowired
	private BookService bookService;
	
	/**
	 * 对某书进行评论
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/comments",  method = RequestMethod.POST,  produces = "application/json;charset=UTF-8")
	public String  commentBook(
		@PathVariable Long id,
		@RequestHeader String authorization,
		@RequestBody Map<String, String> comment
	) {
		String content = comment.get("content");
		String score = comment.get("score");
		Message message = bookService.commentBook(authorization, id, content, score);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	/**
	 * 收藏某本书
	 * @param id
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value ="/{id}/collection", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String collectionBook(
			@PathVariable Long id,
			@RequestHeader String authorization
			){
		Message message = bookService.collectionBook(authorization, id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
		
	}
	/**
	 * 查询当前用户收藏的书籍
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value = "/books_collection",  method = RequestMethod.GET,  produces = "application/json;charset=UTF-8")
	public String booksCollection(
			@RequestHeader String authorization
			) {
		Message message = bookService.queryCollectionBook(authorization);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 查询某本书的评论
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/comments" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String comments (
			@PathVariable Long id
			) {
		Message message = bookService.QueryBookComment(id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	/**
	 * 添加书本
	 * @param book
	 * @param name
	 * @param picture
	 * @param cover
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = "multipart/form-data")
	public String addBookk(
			Book book,
			@RequestParam(value = "picture" , required = false) CommonsMultipartFile picture,
			@RequestParam(value = "cover" , required = false) CommonsMultipartFile cover
			) {
		Message message = bookService.addBook(book, cover, picture);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	/**
	 * 查询某本书的信息
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/{id}" , method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String queryOneBook(
				@PathVariable Long id
				) {
		Message message = bookService.queryOneBook(id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 查询所有书本的信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String queryAllBook() {
		Message message = bookService.queryAllBook();
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}

	/**
	 * 删除某本书
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String delete(
			@PathVariable Long id
			) {
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
	 * 设置喜欢某本书
	 * @param id
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value = "/{id}/like", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String likeBook(
			@PathVariable Long id,
			@RequestHeader String authorization
			){
		Message message = bookService.likeBook(authorization, id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 设置不喜欢某本书
	 * @param id
	 * @param authorization
	 * @return
	 */
	@RequestMapping(value = "/{id}/unlike", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String unlikeBook(
			@PathVariable Long id,
			@RequestHeader String authorization
			){
		Message message = bookService.unlikeBook(authorization, id);
		Response response = new Response(message);
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
	
	/**
	 *  最新的书
	 * @return
	 */
	@RequestMapping(value="/isLatest",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String getLatestBook(){
		Message message= bookService.getLatestBook();
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 获得评论数最多的5个书
	 * @return
	 */
	@RequestMapping(value="/isFamous",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String getFamousBook(){
		Message message=bookService.getFamousBook();
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
}
