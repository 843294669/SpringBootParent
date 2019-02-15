package com.example.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}

/**
 * 打war包
 */
/*
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
*/
