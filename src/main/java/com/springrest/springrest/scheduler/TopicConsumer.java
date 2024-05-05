package com.springrest.springrest.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import com.google.gson.Gson;
import com.springrest.springrest.modal.MailInfo;
import com.springrest.springrest.service.MailService;

@Configuration
public class TopicConsumer {
	
	@Autowired
	private MailService mailService;
	
	@KafkaListener(topics = AppConfig.NEWS_TOPIC_NAME, groupId = AppConfig.GROUP_ID)
	public void endEmailOnLiveDate(String value) {
		System.out.println("consumer  " + value);
		try {
			MailInfo info = new Gson().fromJson(value, MailInfo.class);
			if(info!=null) {
				mailService.sendSimpleMail(info);
			}
		} catch (Exception e) {
			
		}
	}
}
