package com.wcg.caoxian.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.wcg.caoxian.user.dao")
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.wcg.caoxian"},excludeFilters=@Filter(type=FilterType.REGEX,pattern={"com.wcg.caoxian.sdk.log.*"}))
public class TestUserDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestUserDomainApplication.class, args);
	}
}
