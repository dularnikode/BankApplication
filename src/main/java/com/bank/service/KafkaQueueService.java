package com.bank.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaQueueService {
	private static final Logger log = LoggerFactory.getLogger(KafkaQueueService.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafka.topic.name}")
	String kafkaTopic;
	
	public void sendMessage(String message) {
		try {
			kafkaTemplate.send(kafkaTopic,message);
		}catch (Exception e) {
			log.info("failed to send message ,message : {}",message);
		}
		log.info("message sent sucessfully,message: {}",message);
	}
}
