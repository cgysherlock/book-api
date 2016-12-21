package com.me.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.me.service.ConcernService;

@RestController
@RequestMapping(value = "")
public class ConcernController extends BaseController {

	@Autowired
	ConcernService concernService;

	/**
	 * 关注他人
	 * itsid被关注的人的id
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public String concernOthers(long itsid, HttpSession session) {
		//得到存在session中的用户id
		long myid=(int)session.getAttribute("myid");
		Message message = concernService.concernOthers(myid,itsid);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}

}
