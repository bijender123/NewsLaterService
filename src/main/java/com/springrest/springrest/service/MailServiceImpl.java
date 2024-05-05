package com.springrest.springrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.springrest.springrest.modal.MailInfo;

@Service
public class MailServiceImpl implements MailService {

	@Autowired private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") private String sender;
    
	@Override
	public String sendSimpleMail(MailInfo details) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
 
            System.out.println("sender " + sender + " receiver " + details.getReceiver());
            message.setFrom(sender);
            message.setTo(details.getReceiver());
            message.setText(details.getBody());
            message.setSubject(details.getSubject());
 
            // Sending the mail
            javaMailSender.send(message);
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
	}

}
