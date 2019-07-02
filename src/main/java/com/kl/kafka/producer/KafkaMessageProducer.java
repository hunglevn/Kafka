package com.kl.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kl.kafka.bean.DocumentStatus;

@Service
public class KafkaMessageProducer {
	@Autowired
	private KafkaTemplate<String, DocumentStatus> kafkaTemplate;

	@Value(value = "${documentstatus.message.topic.name}")
	private String documentStatusTopicName;

	public void sendDocumentStatusMessage(DocumentStatus documentStatus) {

		ListenableFuture<SendResult<String, DocumentStatus>> future = kafkaTemplate.send(documentStatusTopicName, documentStatus);
		future.addCallback(new ListenableFutureCallback<SendResult<String, DocumentStatus>>() {

			@Override
			public void onSuccess(SendResult<String, DocumentStatus> result) {
				System.out.println(
						"Sent message=[" + documentStatus + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + documentStatus + "] due to : " + ex.getMessage());
			}
		});
	}
}
