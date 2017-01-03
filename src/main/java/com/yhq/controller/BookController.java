package com.yhq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@RequestMapping(method = RequestMethod.POST, produces = "multipart/form-data")
	public String addBookk(
			Book book,
			@RequestParam("name") String name,
			@RequestParam(value = "picture" , required = false) CommonsMultipartFile picture,
			@RequestParam(value = "cover" , required = false) CommonsMultipartFile cover
			) {
		System.out.println("添加书本的名字为" + name);
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
}
