package com.kl.kafka.producer;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.test.context.junit4.SpringRunner;

import com.kl.kafka.KafkaApplication;
import com.kl.kafka.bean.DocumentStatus;
import com.kl.kafka.bean.Status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class KafkaMessageProducerTest {
	@Autowired
	private KafkaMessageProducer kafkaMessageProducer;
	
	@Autowired
	private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;
	
	public void stopListeners() {
		System.out.println("Stop listener");
		Collection<MessageListenerContainer> listenerContainers = kafkaListenerEndpointRegistry.getListenerContainers();
		for (MessageListenerContainer messageListenerContainer : listenerContainers) {
			System.out.println(messageListenerContainer);
			messageListenerContainer.stop();
			
		}
	}
	
	public void startListeners() {
		System.out.println("Start listener");
		Collection<MessageListenerContainer> listenerContainers = kafkaListenerEndpointRegistry.getListenerContainers();
		for (MessageListenerContainer messageListenerContainer : listenerContainers) {
			messageListenerContainer.start();
			
			
		}
	}
	
	@Test
	public void testSendDocumentStatusMessage() {
		//stop all listener to prevent consumer messages.
		stopListeners();
		for(int i = 0; i < 1; i++) {
			kafkaMessageProducer.sendDocumentStatusMessage(new DocumentStatus("1", Status.Success));
		}
	}
	
	@Test
	public void testReceiveDocumentStatusMessage() {
		//stop all listener to prevent consumer messages.
		startListeners();
	}
	
	@Test
	public void waitForCompletion() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
