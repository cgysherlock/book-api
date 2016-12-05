package com.yhq.controller;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.me.controller.BaseController;
import com.me.model.Page;
import com.me.model.Pageable;
import com.me.model.Message;
import com.yhq.model.Equipment;
import com.yhq.model.User;
import com.yhq.service.EquipmentService;
import com.yhq.service.LoginService;

@Controller
@RequestMapping(value="/")
public class LoginController extends BaseController{
	
	@Autowired
	LoginService loginService;
	@Autowired
	EquipmentService equipmentService;
	
	@ResponseBody
	@RequestMapping(value="/list")
	public String list() {
		Pageable pageable = new Pageable();
		pageable.setPageNumber(2);
		Page<Equipment> page = equipmentService.pageable(pageable);
		String json = JSONArray.toJSONString(page.getList());
		return json;
	}
	
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/regist")
	public String regist() {
		return "regist";
	}
	
	@RequestMapping(value="/loginOut")
	public String loginOut(HttpSession session) {
		session.invalidate();
		return "login";
	}
	
	@RequestMapping(value="/doLogin")
	public String doLogin(String username, String password, String type, HttpSession session, HttpServletRequest request) {
		Message message = loginService.doLogin(username, password, type, session);
		if (message.getType().equals("success")) {
			return "redirect:/index";
		} else {
			request.setAttribute("message", message);
			return "login";
		}
	}
	
	@RequestMapping(value="/doRegist")
	public ModelAndView regist(User user, HttpSession session) {
		String path = "";
		Message message = loginService.doRegist(user, session);
		ModelAndView modelAndView = new ModelAndView();
		path = message.getType().equals("success") ? "equipment/index" : "regist";
		modelAndView.setViewName(path);
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value="/index")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("equipment/index");
		List<User> users = loginService.getUsers();
		List<Equipment> equipments = equipmentService.getEquipments();
		modelAndView.addObject("users", users);
		modelAndView.addObject("equipments", equipments);
		return modelAndView;
	}
	
	
}
