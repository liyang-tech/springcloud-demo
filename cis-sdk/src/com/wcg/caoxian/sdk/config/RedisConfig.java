package com.wcg.caoxian.sdk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@Configuration
public class RedisConfig {

	@Autowired
	@Qualifier("redisConnectionFactory")
	private RedisConnectionFactory redisConnectionFactory;
	
	@Bean(name="masterRedisTemplate")
	public RedisTemplate<String, String> redisTemplate(){
		StringRedisTemplate template = new StringRedisTemplate(redisConnectionFactory);
		template.afterPropertiesSet();
		return template;
	}
}
