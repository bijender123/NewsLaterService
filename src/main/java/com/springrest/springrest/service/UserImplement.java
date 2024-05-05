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
		Map<String, Object> map = new HashMap<>();
		map.put("email", userEmail);
		try {
    		Integer user_id = dbAdaptor.createUser(userEmail);
    		if(user_id>0) {
    			map.put("user_id", user_id);
    			return getSuccessResponse(map);
    		}else {
    			map.put("error", "DB Error");
    			return getFailureResponse(map);
    		}
    	}catch(Exception e){
    		
    	}
		map.put("error", "Internal Server Error");
		return getFailureResponse(map);
	}
	
	@Override
	public String addTopicstoUser(Integer user_id, List<Integer> topicIDs) {
		Map<String, Object> map = new HashMap<>();
		try {
			if(dbAdaptor.addTopicUserMapping(user_id, topicIDs)) {
				map.put("message", "Topic added to user");
				return getSuccessResponse(map);
			}else {
				map.put("message", "Failed to add topic to user");
				map.put("error", "DB Error");
				return getSuccessResponse(map);
			}
		}catch (Exception e) {
		}
		map.put("error", "Internal Server Error");
		return getFailureResponse(map);
	}

    
    private String getSuccessResponse(Map<String, Object> response) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS, SUCCESS);
        map.put(RESPONSE, response);
        return gson.toJson(map);
    }
    
    private String getFailureResponse(Map<String, Object> response) {
        Map<String, Object> map = new HashMap<>();
        map.put(STATUS, ERROR);
        map.put(RESPONSE, response);
        return gson.toJson(map);
    }
	
}
