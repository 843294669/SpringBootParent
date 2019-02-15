package com.example.boot.redis;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootConfiguration
@EnableRedisHttpSession
public class RedisSessionConfig {

}
