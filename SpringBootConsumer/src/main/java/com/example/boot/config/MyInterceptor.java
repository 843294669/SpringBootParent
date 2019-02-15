package com.example.boot.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义拦截器
 * @author Administrator
 *
 */
public class MyInterceptor implements HandlerInterceptor {

	/**
	 * 在请求处理之前进行调用
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String accessToken = request.getHeader("accessToken");
		String userName = (String) request.getSession().getAttribute(accessToken);
		if(!org.apache.commons.lang.StringUtils.equals(accessToken, userName)) {
			return false;
		}
		//只有返回true才会继续向下执行，返回false取消当前请求
		return true;
	}

	/**
	 * 请求处理之后进行调用，但是在视图被渲染之前
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle: 请求处理之后进行调用，但是在视图被渲染之前");
	}

	/**
	 * 整个请求结束之后被调用
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}

}
