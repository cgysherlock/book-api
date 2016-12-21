package com.yhq.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.model.Register;
import com.yhq.service.AuthService;


@RestController
@RequestMapping(value="/token")
public class AuthController extends BaseController{
	
	@Autowired
	AuthService authService;

	@RequestMapping(method=RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String getToken(
		@RequestBody Register register
	) {
		String tel = register.getTel();
		String password = register.getPassword();
		String smsCode = register.getSmsCode();
		Message message = authService.verification(tel, password, smsCode);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}
	
	
}
