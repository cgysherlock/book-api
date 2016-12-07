package com.me.plugin;

import com.taobao.api.ApiException;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class SMSCode {
	
	public static final String URL = "http://gw.api.taobao.com/router/rest"; 
	public static final String APP_KEY = "23552052";
	public static final String SECRET = "6fd739f6df2be9948563d4ae53570ae9";
	public static final String FREE_SIGN_NAME = "上书房";
	public static final String SMS_TEMPLATE_CODE = "SMS_31315032";
	
	public static final String TEST_URL = "http://gw.api.tbsandbox.com/router/rest";
	public static final String TEST_APP_KEY = "appkey";
	public static final String TEST_SECRET = "appsecret";
	
	public static String sendSMSCode(String code, String tel) {
		TaobaoClient client = new DefaultTaobaoClient(URL, APP_KEY, SECRET, "json");
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType( "normal" );
		req.setSmsFreeSignName( FREE_SIGN_NAME );
		req.setSmsParamString( "{code:\""+code+"\"}" );
		req.setRecNum( tel );
		req.setSmsTemplateCode( SMS_TEMPLATE_CODE );
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rsp.getBody();
	}
	
	public static void main(String[] args) {
		SMSCode.sendSMSCode("1234", "17826806572");
	}
}
