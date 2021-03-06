package com.springboot.kafka.Consumer.models;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;


public class JsonConfiguration {

	@Autowired
	private ConfigProperties configProperties;
	
	@Bean
	public ConsumerFactory<String, FundTransferRequest> consumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, this.configProperties.getBrokerAddress());
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "fundrequestgroup");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 15000);
		JsonDeserializer<FundTransferRequest> jsonDeserializer = new JsonDeserializer<>(FundTransferRequest.class);
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), jsonDeserializer);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, FundTransferRequest> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, FundTransferRequest> factory =
				new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}

}
