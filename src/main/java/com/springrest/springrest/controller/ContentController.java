package com.springrest.springrest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.springrest.springrest.modal.Content;
import com.springrest.springrest.service.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	private ContentService contentservice;

	@GetMapping("/test")
	public String test() {
		return "this is working on content side";
	}
	
	@PostMapping("/add")
    public String addContent(@RequestBody Map<String, String> requestBody) {
		Content content = new Content();
		content.setContent_title(((String)requestBody.get("title")).trim());
		content.setContent_textString(((String)requestBody.get("text")).trim());
		content.setLive_date(((String)requestBody.get("live_date")).trim());
		content.setTopic_id(Long.parseLong(requestBody.get("topic_id")));
		
		System.out.println("object " + new Gson().toJson(content));
    	
    	return contentservice.addContent(content);
    }

}
