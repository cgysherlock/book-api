package com.yhq.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.model.Register;
import com.yhq.service.AuthService;
import com.yhq.service.UserService;


@RestController
@RequestMapping(value="/user")
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
	
	@RequestMapping(value="/{id}/concern",method=RequestMethod.GET,produces = "application/json;charset=UTF-8")
	public String addConcern(
		@PathVariable(name="id") Long concernerId,
		@RequestParam Long concernedId
	){
		Message message=userService.addConcern(concernerId, concernedId);
		Response response=new Response();
		response.setMessage(message);
		return HttpKit.toJson(response);
	}

	@RequestMapping(value="/{id}/followers",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public String getFollowers(
		@PathVariable(name="id") Long concernerId
	){
		Message message=userService.getFollowers(concernerId);
		Response response=new Response(message);
		return HttpKit.toJson(response);
	}
	
}
