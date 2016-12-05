package com.yhq.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		printRequest(request);
		String base = request.getContextPath();
		if (request.getSession().getAttribute("user") == null && !notIntercept(request)) {
			response.sendRedirect(base + "/login");
			return false;
		}
		request.setAttribute("base", base);
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
		String[] actions = {"login", "doLogin", "doRegist", "regist", "list"};
		for(String s : actions){
			if((pre+s).equals(action)){
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
