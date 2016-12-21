package com.yhq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.me.controller.BaseController;
import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.model.Message;
import com.yhq.service.ConcernService;


@RestController
@RequestMapping(value = "/concern")
public class ConcernController extends BaseController {

	@Autowired
	ConcernService concernService;

	/**
	 * 关注他人
	 * itsid被关注的人的id
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String concernOthers(Long concernerId, Long concernedId) {
		//得到存在session中的用户id
		Message message = concernService.concernOthers(concernerId,concernedId);
		Response response = new Response(message);
		return HttpKit.toJson(response);
	}

}
