package com.wcg.caoxian.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.wcg.caoxian.zuul.filter.PreFilter;

@EnableZuulProxy
@SpringBootApplication
public class ZuulServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}
	
	@Bean
	PreFilter preFilter(){
		return new PreFilter();
	}
	
	@Bean
	CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource sourse = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		sourse.registerCorsConfiguration("/**", config);
		return new CorsFilter(sourse);
	}
	
}
