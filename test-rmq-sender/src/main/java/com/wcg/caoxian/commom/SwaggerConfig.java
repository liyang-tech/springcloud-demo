package com.wcg.caoxian.commom;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {
	
	@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).pathMapping("/").select()
				.apis(RequestHandlerSelectors.basePackage("com.wcg.caoxian"))
				.build().apiInfo(apiInfo());
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		
		ApiInfo apiInfo = new ApiInfo("RMQ发送测试", 
				"RMQ Platform's REST DOMAIN", 
				"1.0", 
				"NO terms of service", 
				"LiYang", 
				"The Apache License, Version 2.0", 
				"http://www.apache.org/licenses/LICENSE-2.0.html"
				);
		
		return apiInfo;
	}
	
}
