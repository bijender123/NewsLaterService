package com.springrest.springrest.service;

import com.springrest.springrest.modal.MailInfo;

public interface MailService {

	public String sendSimpleMail(MailInfo details);
}
