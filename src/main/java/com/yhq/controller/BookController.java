package com.yhq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.service.BookService;

@Controller
@RequestMapping(value = "/book")
public class BookController extends BaseController{
	@Autowired
	private BookService bookService;
	
	/**
	 * 获取所有书本
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAllBook() {
		Message message = bookService.getAllBook();
		Response response =  new Response(message);
		return HttpKit.toJson(response);
	}
	
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String add(
			 String type
			) {
		Message message = bookService.getBookByType(type);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String delete(@PathVariable Long id) {
		Message message = bookService.deleteBook(id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
}
