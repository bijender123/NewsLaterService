package com.springrest.springrest.scheduler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.springrest.springrest.modal.MailInfo;
import com.springrest.springrest.repo.DbAdaptor;

@Component
public class KafkaJobScheduler {
	
	@Autowired
	private DbAdaptor dbAdaptor;
	
	@Autowired
	private KafkaTemplate<String, String>  topicKafkaTemplate;
	
	//FixedRate is in milisecs(running every 1 hour)
	@Scheduled(fixedRate = 3600000L)
	public void addEmailContentToQueue() {
		
		try {
			List<MailInfo> mailList = dbAdaptor.getEmailData();
			System.out.println("obj " + new Gson().toJson(mailList));
			for(MailInfo info : mailList) {
				this.topicKafkaTemplate.send(AppConfig.NEWS_TOPIC_NAME,new Gson().toJson(info));
			}
		}catch(Exception e){
			
			System.out.println("error while mailing object " + e);
		}
	}

}
