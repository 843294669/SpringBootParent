package com.example.boot.feign;

import com.example.boot.config.MyRequestInterceptor;
import com.example.boot.vo.SbUser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 定义远程应用服务名称
 * @author Administrator
 *
 */
@FeignClient(name = "provider", fallbackFactory = FeignFailBackFactory.class , configuration = MyRequestInterceptor.class)
public interface FeignInterface {
	
	@GetMapping("/listUser")
	public SbUser getUserInfo();
	
}
