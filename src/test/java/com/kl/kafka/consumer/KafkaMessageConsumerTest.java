package com.kl.kafka.consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kl.kafka.KafkaApplication;
import com.kl.kafka.producer.KafkaMessageProducer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class KafkaMessageConsumerTest {
	@Autowired
	private KafkaMessageProducer kafkaMessageProducer;
	
	@Test
	public void testSendDocumentStatusMessage() {
	}
}
