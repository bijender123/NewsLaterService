package com.springrest.springrest.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.springrest.springrest.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userservice;

	@GetMapping("/test")
	public String test() {
		return "this is working";
	}
	
	@PostMapping("/create")
    public String createUser(@RequestBody Map<String, String> requestBody) {
    	String userEmail = requestBody.get("email").trim();
    	System.out.println("email " + userEmail);
    	
    	return userservice.addUser(userEmail);
    }
	
	@PostMapping("/addUserTopics")
    public String addUserTopics(@RequestBody Map<String, String> requestBody) {
		Long user_id = Long.valueOf(requestBody.get("user_id").toString());
		String topicStr = (String)requestBody.get("topics");
	
		List<String> topics = new ArrayList<String>(Arrays.asList(topicStr.split(",")));
		System.out.println("request " + new Gson().toJson(requestBody));
    	return userservice.addTopicstoUser(user_id, topics);
    }
	
}
