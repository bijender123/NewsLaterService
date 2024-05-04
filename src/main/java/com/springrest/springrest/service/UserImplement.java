package com.springrest.springrest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springrest.springrest.repo.DbAdaptor;

@Service
public class UserImplement implements UserService {
	
	@Autowired
	private DbAdaptor dbAdaptor;
	
	private static final Gson gson = new GsonBuilder().create();

    private static final String RESPONSE = "response";
    private static final String SUCCESS = "success";
    private static final String MESSAGE = "message";
    private static final String STATUS = "status";
    private static final String ERROR = "error";

	@Override
	public String addUser(String userEmail) {
		
		try {
    		if(dbAdaptor.createUser(userEmail)) {
    			String message = "User created with emailID - " + userEmail;
        		return getSuccessResponse(message);
        	}else {
        		String message = "User already exists for emailID - " + userEmail;
        		return getFailureResponse(message);
        	}
    	}catch(Exception e){
    		
    	}
		return getFailureResponse("API Error");
	}
    
    private String getSuccessResponse(String message) {
        Map<String, String> map = new HashMap<>();
        map.put(RESPONSE, SUCCESS);
        map.put(STATUS, SUCCESS);
        map.put(MESSAGE, message);
        return gson.toJson(map);
    }
    
    private String getFailureResponse(String message) {
        Map<String, String> map = new HashMap<>();
        map.put(RESPONSE, SUCCESS);
        map.put(STATUS, ERROR);
        map.put(MESSAGE, message);
        return gson.toJson(map);
    }
	
}
