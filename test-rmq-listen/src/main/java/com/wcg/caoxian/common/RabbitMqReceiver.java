package com.wcg.caoxian.common;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqReceiver {

	@RabbitListener(queues="q.time")
	public void haldMqMessage(Object obj){
		try {
			Message message = (Message) obj;
			byte[] msg = message.getBody();
			String str = new String(msg, "utf-8");
			System.out.println("监听接收到MQ,时间===="+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"消息体是===="+str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
