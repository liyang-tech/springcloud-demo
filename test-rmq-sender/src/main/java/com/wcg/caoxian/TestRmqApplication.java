package com.wcg.caoxian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.wcg.caoxian"})
public class TestRmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestRmqApplication.class, args);
	}
}
