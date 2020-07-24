package com.wcg.caoxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.wcg.caoxian"},excludeFilters=@Filter(type=FilterType.REGEX,pattern={"com.wcg.caoxian.sdk.log.*"}))
public class TestRmqListenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestRmqListenApplication.class, args);
	}
}
