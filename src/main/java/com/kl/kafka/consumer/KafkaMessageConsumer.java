package com.kl.kafka.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kl.kafka.bean.DocumentStatus;

@Service
public class KafkaMessageConsumer {
	@Value(value = "${documentstatus.message.group.id}")
	private String documentStatusMessageGroupId;

	@KafkaListener(topics = "${documentstatus.message.topic.name}", groupId = "${documentstatus.message.group.id}", containerFactory = "documentStatusKafkaListenerContainerFactory")
	public void listenDocumentStatus(DocumentStatus documentStatus) {
		System.out.println("Received Messasge in group '" + documentStatusMessageGroupId + "': " + documentStatus);
	}
}
