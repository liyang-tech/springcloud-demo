package com.wcg.caoxian.sdk.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

@Configuration
@ConfigurationProperties(prefix="spring.redis")
public class RedisConnectionFactoryConfig {

	private String host;
	
	private int port;
	
	private int database;
	
	@Bean("redisConnectionFactory")
	public RedisConnectionFactory masterRedisConnectionFactory(){
		JedisConnectionFactory jcf = new JedisConnectionFactory();
		jcf.setHostName(host);
		jcf.setPort(port);
		jcf.setDatabase(database);
		jcf.setUsePool(true);
		return jcf;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getDatabase() {
		return database;
	}

	public void setDatabase(int database) {
		this.database = database;
	}
	
}
