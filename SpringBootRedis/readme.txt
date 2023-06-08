注意：Spring Boot 2.4.0+ 默认装配LettuceConnectionFactory，使用Jedis可能会有一些日志冲突和版本兼容问题，慎用。
同时，配置文件也有变化：
spring:
  data:
    redis:
      host: ******
      port: 13325
      database: 0
      client-type: lettuce
      lettuce:
        pool:
          max-active: 8
          max-idle: 8
          min-idle: 0
          max-wait: 3000
