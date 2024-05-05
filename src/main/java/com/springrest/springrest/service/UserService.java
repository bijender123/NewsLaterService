package com.springrest.springrest.service;

import java.util.List;

public interface UserService {

	public String addUser(String email);
	public String addTopicstoUser(Integer user_id, List<Integer> topicIDs);

}
