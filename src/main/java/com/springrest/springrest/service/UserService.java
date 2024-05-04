package com.springrest.springrest.service;

import java.util.List;

public interface UserService {

	public String addUser(String email);
	public String addTopicstoUser(Long user_id, List<String> topicIDs);

}
