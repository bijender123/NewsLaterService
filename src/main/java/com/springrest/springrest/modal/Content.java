package com.springrest.springrest.modal;

public class Content {
	
	public String content_title;
	public String content_textString;
	public Long topic_id;
	public String live_date;
	public Content(String content_title, String content_textString, Long topic_id, String live_date) {
		super();
		this.content_title = content_title;
		this.content_textString = content_textString;
		this.topic_id = topic_id;
		this.live_date = live_date;
	}
	public Content() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getContent_title() {
		return content_title;
	}
	public void setContent_title(String content_title) {
		this.content_title = content_title;
	}
	public String getContent_textString() {
		return content_textString;
	}
	public void setContent_textString(String content_textString) {
		this.content_textString = content_textString;
	}
	public Long getTopic_id() {
		return topic_id;
	}
	public void setTopic_id(Long topic_id) {
		this.topic_id = topic_id;
	}
	public String getLive_date() {
		return live_date;
	}
	public void setLive_date(String live_date) {
		this.live_date = live_date;
	}
	

}
