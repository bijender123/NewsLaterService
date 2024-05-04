package com.springrest.springrest.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
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
    private static final String STATUS = "status";
    private static final String ERROR = "error";

	@Override
	public String addUser(String userEmail) {
		String message  = "";
		try {
    		if(dbAdaptor.createUser(userEmail)) {
    			message = "User created with emailID - " + userEmail;
        		return getSuccessResponse(message);
        	}else {
        		message = "User already exists for emailID - " + userEmail;
        		return getFailureResponse(message);
        	}
    	}catch(Exception e){
    		
    	}
		return getFailureResponse("Internal Server Error");
	}
	
	@Override
	public String addTopicstoUser(Long user_id, List<String> topicIDs) {
		String message = "";
		try {
			if(dbAdaptor.addTopicUserMapping(user_id, topicIDs)) {
				message = "Topic added to user";
				return getSuccessResponse(message);
			}else {
				message = "Failed to add topic to user";
				return getSuccessResponse(message);
			}
		}catch (Exception e) {
		}
		return getFailureResponse("Internal Server Error");
	}

    
    private String getSuccessResponse(String message) {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, SUCCESS);
        map.put(RESPONSE, message);
        return gson.toJson(map);
    }
    
    private String getFailureResponse(String message) {
        Map<String, String> map = new HashMap<>();
        map.put(STATUS, ERROR);
        map.put(RESPONSE, message);
        return gson.toJson(map);
    }
	
}
