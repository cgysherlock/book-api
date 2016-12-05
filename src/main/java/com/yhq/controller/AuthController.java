package com.yhq.controller;


import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.me.plugin.Redis;
import com.yhq.service.AuthService;


@RestController
@RequestMapping(value="/auth/token")
public class AuthController extends BaseController{
	
	@Autowired
	AuthService authService;

	@RequestMapping(method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getToken(
		@Param("tel") String tel, 
		@Param("password") String password
	) {
		Message message = authService.verification(tel, password);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
}
