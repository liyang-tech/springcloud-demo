package com.wcg.caoxian.bes;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.wcg.caoxian.bes.dao")
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.wcg.caoxian"},excludeFilters=@Filter(type=FilterType.REGEX,pattern={"com.wcg.caoxian.sdk.log.*"}))
public class PersonalBesManageApplication {
	//@SpringBootApplication 整合了
    //							@Configuration
    //							@EnableAutoConfiguration
    //							@ComponentScan
	public static void main(String[] args) {
		SpringApplication.run(PersonalBesManageApplication.class, args);
	}
}
