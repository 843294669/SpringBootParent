package com.example.boot.feign;

import com.example.boot.vo.SbUser;

import org.springframework.stereotype.Component;

/**
 * 熔断处理，服务降级，必须是@Component或者@Service注解
 * 如果不使用Feign，使用hystrix需要在熔断方法上加注解@HystrixCommand。
 * @author Administrator
 *
 */
@Component
public class FeignFailBackFactory implements FeignInterface {

	@Override
	//@HystrixCommand
	//@HystrixCommand：此注解表示此方法是hystrix方法，其中fallbackMethod定义回退方法的名称。
	public SbUser getUserInfo() {
		return new SbUser();
	}

}
