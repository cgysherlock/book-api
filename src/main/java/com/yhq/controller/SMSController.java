package com.yhq.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.me.controller.BaseController;
import com.me.http.Response;
import com.me.http.Session;
import com.me.kit.StrKit;
import com.me.plugin.SMSCode;

@RestController
@RequestMapping(value="/SMSCode")
public class SMSController extends BaseController{
	
	@RequestMapping(method=RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getToken(
		@Param("tel") String tel
	) {
		String smsCode = StrKit.generatorNumberCode(4);
		String msg = SMSCode.sendSMSCode(smsCode, tel);
		String result = "";
		JSONObject json = JSONObject.parseObject(msg);
		if (json != null && json.getJSONObject("alibaba_aliqin_fc_sms_num_send_response")
				.getJSONObject("result")
				.getBooleanValue("success")) {
			result = Response.success("获得验证码成功");
			Session.put(tel, smsCode);
		} else {
			result = Response.error("获验证码失败");
		}
		return result;
	}
}
