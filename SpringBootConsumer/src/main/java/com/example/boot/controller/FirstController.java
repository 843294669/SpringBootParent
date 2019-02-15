package com.example.boot.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.boot.config.RedisLockService;
import com.example.boot.feign.FeignInterface;
import com.example.boot.service.FirstService;
import com.example.boot.vo.SbUser;

@Controller
public class FirstController {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private FirstService service;
	
	@Autowired
	private FeignInterface feignClient;
	
	@Autowired
	private RedisLockService redisLockService;
	
	@RequestMapping("/firstPage")
	public String firstPage(Model model) {
		model.addAttribute("key", "模板数据");
		//查找资源文件下templates中的模板
		return "index";
	}
	
	@RequestMapping("/listUser")
	@ResponseBody
	public SbUser listUser(@ModelAttribute("author") String author) throws SQLException, InterruptedException {
		System.out.println("获取全局异常中设定的参数：" + author);
		SbUser user = service.getUser();
		System.out.println(user.toString());
		return user;
	}
	
	@RequestMapping("/getUser")
	@ResponseBody
	public SbUser getUser(ModelMap modelMap) throws SQLException, InterruptedException {
		//modelMap参数可以获取全局异常设置的参数。
		System.out.println("获取全局异常中设定的参数：" + modelMap.get("author"));
		return feignClient.getUserInfo();
	}
	
	@RequestMapping("/redisLock")
	@ResponseBody
	public int redisLock(HttpServletRequest request) {
		String identifier = redisLockService.lock("lisi");
		System.out.println("客户端锁定："+request.getRemotePort());
		redisLockService.releaseLock("lisi", identifier);
		return request.getRemotePort();
	}
	
	/**
	 * Redis共享Session
	 * @param request
	 * @return
	 */
	@GetMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request) {
		String result = null;
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		if(org.apache.commons.lang.StringUtils.isEmpty(userName)) {
			result = "请输入用户名登录";
		}
		else {
			session.setAttribute(userName, userName);
			result = "用户["+userName+"]登录成功";
		}
		return result;
	}
	
}
