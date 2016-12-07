package com.me.interceptor;

import java.io.PrintWriter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.me.http.HttpKit;
import com.me.http.Response;
import com.me.http.Session;
import com.me.http.Token;
import com.me.kit.StrKit;
import com.me.model.Message;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		printRequest(request);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		String access_token = request.getHeader("Authorization");
		Token token = StrKit.notBlank(access_token) ? (Token)Session.get(access_token) : null;
		Message message = Token.checkToken(token);
		if ((StrKit.isBlank(access_token) || message.getType() != "success") && !notIntercept(request)) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			Response res = new Response();
			res.setMessage(Message.error("验证失败，请重新登录"));
			PrintWriter out = response.getWriter();
			out.println(HttpKit.toJson(res));
			out.close();
			return false;
		}
		return true;
	}

	/**
	 * 判断是否拦截
	 * @param request
	 * @return
	 */
	private boolean notIntercept(HttpServletRequest request) {
		String pre = "/";
		String base = request.getContextPath();
		String uri = request.getRequestURI();
		String action = uri.replaceFirst(base, "");
		String[] actions = {"login", "regist", "SMSCode", "token"};
		for(String s : actions){
			if((pre + s).equals(action)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	/**
	 * 打印请求的数据
	 * @param request
	 */
	public void printRequest(HttpServletRequest request){
		Map<String, String[]> map = request.getParameterMap();
		String uri = request.getRequestURI();
		StringBuilder sb = new StringBuilder("！-----------------------------------------------------！\n");
		sb.append("action: "+uri.substring(uri.lastIndexOf("/")+1)+"\n");
		sb.append("method: "+request.getMethod()+"\n");
		sb.append("paras:  ");
		for(String key : map.keySet()){
			if(map.get(key).length > 1){
				String str = "";
				for(String s : map.get(key)){
					str += s+",";
				}
				sb.append(key+"="+str+"  ");
			}else{
				sb.append(key+"="+map.get(key)[0]+"  ");
			}
			
		}
		sb.append("\n");
		sb.append("-------------------------------------------------------\n");
		System.out.println(sb.toString());
	}

}
