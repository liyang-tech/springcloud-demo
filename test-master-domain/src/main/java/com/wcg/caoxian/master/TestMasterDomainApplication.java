package com.wcg.caoxian.master;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.wcg.caoxian.master.dao")
@EnableTransactionManagement
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages={"com.wcg.caoxian"},excludeFilters=@Filter(type=FilterType.REGEX,pattern={"com.wcg.caoxian.sdk.log.*"}))
public class TestMasterDomainApplication {

	/*@Bean
	@LoadBalanced
	public RestTemplate restTemplate(){
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		messageConverters.add(new AllEncompassingFormHttpMessageConverter());
		RestTemplate template = new RestTemplate(messageConverters);
		return template;
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(TestMasterDomainApplication.class, args);
	}
	
}
