package com.yhq.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.service.UserService;


@RestController
@RequestMapping(value="/users")
public class UserController extends BaseController{
	
	@Autowired
	UserService userService;

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
	public String delete(
		@PathVariable Long id
	) {
		Message message = userService.delete(id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String query(
		@PathVariable Long id
	) {
		Message message = userService.getById(id);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 添加关注
	 * @param concernerId 关注人id
	 * @param concernedId 被关注人的id
	 * @return
	 */
	@RequestMapping(value="/{userId}/concern",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String addConcern(
		@PathVariable(name="userId") Long concernerId,
		@RequestParam Long concernedId
	){
		Message message=userService.addConcern(concernerId, concernedId);
		Response response=new Response();
		response.setMessage(message);
		return HttpKit.toJson(response);
	}

	/**
	 * 获取粉丝
	 * @param concernedId 要获取粉丝的用户id
	 * @return
	 */
	@RequestMapping(value="/{userId}/concerneds",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String getFollowers(
		@PathVariable(name="userId") Long concernedId
	){
		Message message=userService.getConcerneds(concernedId);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 删除关注
	 * @param concernerId 关注人的id
	 * @param concernedId 被关注的id
	 * @return
	 */
	@RequestMapping(value="/{userId}/delete_concern/{id}",method=RequestMethod.DELETE,produces="application/json;charset=UTF-8")
	public String deleteConcern(
		@PathVariable(name="userId") Long concernerId,@PathVariable(name="id") Long concernedId 
	){
		Message message=userService.deleteConcern(concernerId, concernedId);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
	/**
	 * 获取粉丝人数最多的三个人
	 * @return
	 */
	@RequestMapping(value="/famousUsers",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String getFamousUser(){
		Message message=userService.getFamousUser();
		Response response =new Response(message);
		return HttpKit.toJson(response);
	}
	
}
