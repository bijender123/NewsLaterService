package com.springrest.springrest.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springrest.springrest.modal.Content;
import com.springrest.springrest.repo.DbAdaptor;

@Service
public class ContentImplement implements ContentService{

	@Autowired
	private DbAdaptor dbAdaptor;
	
	private static final Gson gson = new GsonBuilder().create();

    private static final String RESPONSE = "response";
    private static final String SUCCESS = "success";
    private static final String STATUS = "status";
    private static final String ERROR = "error";

    
	@Override
	public String addContent(Content news_content) {
		String message = "";
		try {
			if(dbAdaptor.addContent(news_content)) {
				message = "content added successfully";
				return getSuccessResponse(message);
			}else {
				message = "content addition failed";
				return getFailureResponse(message);
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
