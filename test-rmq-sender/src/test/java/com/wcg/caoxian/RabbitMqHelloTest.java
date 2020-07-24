package com.wcg.caoxian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wcg.caoxian.commom.MqSender;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

	@Autowired
	private MqSender rabbitMQSender;
	
	@Test
    public void hello() throws Exception {
		 rabbitMQSender.sender();
    }
	
}
