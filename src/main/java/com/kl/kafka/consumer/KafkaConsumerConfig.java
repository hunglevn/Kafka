package com.kl.kafka.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.kl.kafka.bean.DocumentStatus;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${documentstatus.message.group.id}")
	private String documentStatusMessageGroupId;

	public ConsumerFactory<String, DocumentStatus> documentStatusConsumerFactory(String groupId) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(DocumentStatus.class));
	}

	@Bean(name = "documentStatusKafkaListenerContainerFactory")
	public ConcurrentKafkaListenerContainerFactory<String, DocumentStatus> documentStatusKafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, DocumentStatus> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(documentStatusConsumerFactory(documentStatusMessageGroupId));
		return factory;
	}
}
