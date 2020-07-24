package com.wcg.caoxian.commom;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import net.minidev.json.JSONObject;

@Configuration
@ConfigurationProperties(prefix="wcg.sender")
public class MqSender {

	public String exchange;

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	public void sender(){
		String param = "测试发送MQ,时间==="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println(param);
		try {
			rabbitTemplate.convertAndSend(exchange, "", param.getBytes("UTF-8"));
		} catch (AmqpException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
