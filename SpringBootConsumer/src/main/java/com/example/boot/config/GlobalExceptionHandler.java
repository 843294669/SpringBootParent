package com.example.boot.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
/**
 * 统一异常处理： 可以分类自定义异常，然后集中在该类里处理。 
 * @author Administrator
 * 
 * @ControllerAdvice 是controller的一个辅助类，最常用的就是作为全局异常处理的切面类。
 * @ControllerAdvice 可以指定扫描范围
 * @ControllerAdvice 约定了几种可行的返回值
 * 直接返回model类的话，需要使用@ResponseBody进行json转换
 * 返回String，表示跳到某个view
 * 返回modelAndView
 * 返回model + @ResponseBody
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器。
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {}
	
	/**
	 * 把值绑定到Model中，使全局@RequestMapping可以获取到该值。
	 * @param model
	 */
	@ModelAttribute
	public void addAttributes(Model model) {
		model.addAttribute("author", "Administrator");
	}

	/**
	 * @ExceptionHandler 指定异常类
	 * @param request
	 * @param e
	 * @return
	 * @throws Exception
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ErrorMessage exceptionHandler(HttpServletRequest request, Exception e)
			throws Exception {
		if (e instanceof NoHandlerFoundException) {
			return new ErrorMessage(101, "Controller不存在!");
		}
		return new ErrorMessage(101, "异常!");
	}

	/**
	 * 自定义异常
	 */
	/*
	@ExceptionHandler(SessionNotFoundException.class)
	@ResponseBody //在返回自定义相应类的情况下必须有，这是@ControllerAdvice注解的规定
	public ErrorMessage sessionNotFoundExceptionHandler( HttpServletRequest request, SessionNotFoundException exception) throws Exception {
		return new ErrorMessage(101, "SessionNotFound!");
	}
	*/

}
