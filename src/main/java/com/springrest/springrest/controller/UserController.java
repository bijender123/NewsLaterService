package com.springrest.springrest.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springrest.springrest.service.UserService;


@RestController
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
	
}
