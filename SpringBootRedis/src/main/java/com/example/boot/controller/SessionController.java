package com.example.boot.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.boot.redis.RedisLockService;

@RestController
public class SessionController {

	@Autowired
	private RedisLockService redisLockService;
	/**
	 * redis sesion共享
	 * @param request
	 * @return
	 */
	@GetMapping("/getSession")
	public String getUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String accessToken = request.getHeader("accessToken");
		String userName = (String) session.getAttribute(accessToken);
		if(org.apache.commons.lang.StringUtils.equals(accessToken, userName)) {
			System.out.println("|-------session共享成功");
		}
		System.out.println("访问端口：" + request.getServerPort());
		return userName;
	}
	
	@RequestMapping("/redisLock")
	@ResponseBody
	public int redisLock(HttpServletRequest request) throws InterruptedException {
		String identifier = redisLockService.lock("lisi");
		System.out.println("客户端锁定："+request.getRemotePort());
		Thread.sleep(5000);
		redisLockService.releaseLock("lisi", identifier);
		return request.getRemotePort();
	}
	
}
