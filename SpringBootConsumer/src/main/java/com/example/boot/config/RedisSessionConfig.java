package com.example.boot.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * 代替默认的session（重要）
 * @author Administrator
 *
 */
@SpringBootConfiguration
@EnableRedisHttpSession
public class RedisSessionConfig {

}
